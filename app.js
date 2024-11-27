const express = require('express')
const app = express()
const handlebars = require('express-handlebars').engine
const bodyParser = require('body-parser')
const cookieParser = require('cookie-parser')
const db = require('./firebase.js').db
const authMiddleware = require('./middlewares/authMiddleware')
const { doc, getDoc, update, deleteDoc, getAuth } = require('./firebase.js')
const { auth, signInWithEmailAndPassword} = require('./firebase2.js')
const methodOverride = require('method-override')
//var {doc, setDoc, addDoc, collection, getDocs, getDoc, updateDoc, deleteDoc} = require("firebase/firestore")


//configurando o handlebars como view engine (motor de templates), e o main como layout padrão
app.engine("handlebars", handlebars({defaultLayout: "main"}))
app.set("view engine", "handlebars")
app.use('/static', express.static(__dirname + '/views/layouts'))

//utiliza o bodyParser para tratar dados passados por fomulários (urlencoded) e configura para receber JSONs
app.use(bodyParser.urlencoded({extended: true}))
app.use(bodyParser.json())
app.use(methodOverride('_method'))

// Adiciona o middleware cookie-parser para ler os cookies
app.use(cookieParser())

// login
app.get("/login", (req, res) => {
    res.render("login")
})


app.get("/", authMiddleware, (req, res) => {
    res.render("home_page", { user: req.user });
});


app.get("/logout", (req, res) => {
    res.clearCookie('token');
    res.redirect("/login"); 
});


app.post("/login", (req, res) => {
    const { email, senha } = req.body;

    signInWithEmailAndPassword(auth, email, senha)
        .then((userCredential) => {
            const user = userCredential.user;
            console.log('Usuário logado:', user.email);

            // Gera o token de ID do usuário
            user.getIdToken()
                .then((idToken) => {
                    // Armazena o token no cookie
                    res.cookie('token', idToken/*, {
                        httpOnly: true,  // O cookie só pode ser acessado pelo servidor
                        secure: false,  // Use `true` em produção com HTTPS
                        maxAge: 3600 * 1000  // 1 hora de expiração
                    }*/);

                    res.redirect('/');  // Redireciona para a página inicial após login
                });
        })
        .catch((error) => {
            console.error("Erro ao fazer login:", error.message);
            res.status(401).send("Login falhou. Verifique suas credenciais.");
        });
});

// app.get("/", function(req, res){
//     res.render("home_page")
// })

app.get("/agendamento", authMiddleware, function(req, res){
    //console.log("Dados do usuário autenticado:", req.user);
    res.render("agendamento",  { user: req.user })
})

app.post("/cadastrarAgendamento", authMiddleware, function(req, res){
    //console.log("Dados do usuário autenticado:", req.user); 

    if (!req.user) {
        return res.status(401).send("Usuário não autenticado. Faça login para agendar.");
    }
    //console.log("Firestore Instance:", db);
    db.collection("consultas").add({ //adiciona um novo documento (addDoc) para a coleção (consultas)
        userid: req.user.uid,
        nome: req.body.nome,
        nascimento: req.body.nascimento,
        telefone: req.body.telefone,
        email: req.body.email,
        datahorario: req.body.datahorario,
        espmedica: req.body.espmedica,
        causa: req.body.causa
    }).then((data)=> { //(então) redireciona para a home ('/')
        res.redirect('/')
    }).catch(function(erro){
        console.log("Ocorreu um erro: "+ erro)
    })
})

app.get("/editarAgendamento/:id", authMiddleware, function(req, res) {
    const id = req.params.id;  // Pega o ID da URL
    const refAgen = db.collection('consultas').doc(id);  // Criando a referência correta do documento

    refAgen.get().then((docSnap) => {
        if (docSnap.exists) {
            // Se o documento existir, passamos os dados para o formulário
            const agendamento = docSnap.data()
            res.render('editarAgendamento', { agendamento: { id, ...agendamento } })
        } else {
            res.status(404).send("Agendamento não encontrado.")
        }
    }).catch((erro) => {
        console.log("Erro ao recuperar o agendamento:", erro)
        res.status(500).send("Erro ao carregar os dados do agendamento.")
    })
});

app.post("/editarAgendamento/:id", authMiddleware, function(req, res) {
    const id = req.params.id;  // ID do agendamento a ser atualizado
    const refAgen = db.collection('consultas').doc(id)  // Referência correta do documento
    const dadosAtualizados = {
        nome: req.body.nome,
        nascimento: req.body.nascimento,
        telefone: req.body.telefone,
        email: req.body.email,
        datahorario: req.body.datahorario,
        espmedica: req.body.espmedica,
        causa: req.body.causa
    };

    refAgen.update(dadosAtualizados)  // Usando a função `update()` do Firebase Admin SDK
        .then(() => {
            res.redirect('/consultaAgendamentos')  // Redireciona após a atualização
        })
        .catch((erro) => {
            console.log("Erro ao atualizar o agendamento:", erro)
            res.status(500).send("Erro ao atualizar o agendamento.")
        })
})


// app.get("/consultaAgendamentos", function(req, res){
//     getDocs(collection(db, "consultas")).then((data) => { //recupera todos os documentos do (consultas)
//         const consultas = [] //array vazio

//         data.forEach((docs) => { //percorre o primise(data) 
//             consultas.push({id: docs.id, data: docs.data()}) //a cada iteração, adiciona-se um objeto dentro do array consultas
//         })

//         res.render('listaAgendamentos', {consultas: consultas})
//     }).catch(function(erro){
//         console.log("Ocorreu um erro: "+ erro)
//     })
// })

app.get("/consultaAgendamentos", authMiddleware, function(req, res) {
    db.collection("consultas").where("userid", "==", req.user.uid).get().then((snapshot) => {
        const consultas = []

        snapshot.forEach((doc) => { 
            consultas.push({ id: doc.id, data: doc.data() })
        });

        res.render('listaAgendamentos', { consultas: consultas });
    }).catch(function(erro) {
        console.log("Ocorreu um erro: " + erro)
        res.status(500).send("Erro ao recuperar os agendamentos.");
    })
})


app.get("/deletarAgendamento/:id", authMiddleware, function(req, res) {
    const id = req.params.id
    const refAgen = db.collection('consultas').doc(id)

    refAgen.delete()
        .then(() => {
            res.redirect("/consultaAgendamentos")
        })
        .catch((erro) => {
            console.log("Erro ao excluir: " + erro)
            res.status(500).send("Erro ao excluir o agendamento.")
        })
})

app.listen(8081, function(){
    console.log("Servidor ativo!\nporta 8081")
})