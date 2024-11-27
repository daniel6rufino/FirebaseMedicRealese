const {initializeApp, cert} = require('firebase-admin/app')
const {getFirestore, doc, getDoc, update, deleteDoc, getAuth  } = require('firebase-admin/firestore')

const serviceAccount = require('./creds.json')

initializeApp( {
    credential: cert(serviceAccount)
})

const db = getFirestore()

module.exports = { db, doc, getDoc, update, deleteDoc, getAuth  }