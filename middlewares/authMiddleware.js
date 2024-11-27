const { getAuth } = require('firebase-admin/auth'); // Firebase Admin para autenticação

function authMiddleware(req, res, next) {
    // Pega o token de autenticação do cookie, e não do cabeçalho
    const token = req.cookies.token;

    if (!token) {
        // Se não encontrar o token, redireciona para a página de login
        return res.redirect("/login");
    }

    // Verificar o token de autenticação
    getAuth()
        .verifyIdToken(token)
        .then((decodedToken) => {
            req.user = decodedToken;  // Decodificar o token e armazenar no objeto `req.user`
            next();  // Chama a próxima rota
        })
        .catch((error) => {
            console.error("Erro ao verificar o token:", error);
            res.status(401).send("Token inválido ou expirado.");
        });
}

module.exports = authMiddleware;
