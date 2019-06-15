const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

exports.simpleDbFunction = functions.database.ref('/messages/{bar}')
	.onCreate((snap,context) => {
		var message = snap.val().message;
		console.log("Message : " +message+"\n");
		var receiver_id = snap.val().receiver_id;
		console.log("Receiver Id: "+receiver_id+"\n");
		var sender_name = snap.val().name;
		console.log("Sender Name : "+sender_name+"\n");

		var ref = admin.database().ref('/User/'+receiver_id+'/message_token');
		return ref.once("value",function(snapshot){
			console.log("In Ref User : " + snapshot.val());
			const payload ={
				notification:{
					title:sender_name+" wants to send you a message",
					body :message
				}
			};
			
			admin.messaging().sendToDevice(snapshot.val(),payload);
		});
		
	});