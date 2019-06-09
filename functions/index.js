
const functions = require('firebase-functions');
const admin = require('firebase-admin');

admin.initializeApp(functions.config().firebase);

//Listen if new message added
exports.pushNotification = functions.database.ref('/messages').onWrite( event => {

    console.log("Notification triggered.");

		return null;
    });
