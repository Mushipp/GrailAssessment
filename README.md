# participantRegistrySystem

##Running the Service and Testing with Postman
To run the service and test it in Postman, first, we need to run MongoDB. For this, two commands are required.

1. mongod
2. mongo

After starting mongo, Run the Spring-boot application

Run the following postman actions:

Use the below API calls for Retrieveing, save/update, delete the participants:

"name": "getAllParticipants",
"method": "GET",
"url": localhost:8081/participants/

"name": "saveOrUpdateParticipant",
"url": localhost:8081/participants/save,
"method": "POST",
"header": [
{
"key": "Content-Type",
"value": "application/json",
"type": "text"
}
],
"Request Body":
{
    "participantReferenceId": "PRM-24",
    "name": "Praveen Mushipatla",
    "phoneNumber": 8989998888,
    "address":
        {
        "addressLine1": "222 Main St",
        "aptUnitNumber": "33",
        "city": "Raleigh",
        "state": "North Carolina",
        "zipCode": 27525
    }
},

"name": "deleteParticipantByReferenceId",
"url": "localhost:8081/participants/delete/PRM-24"

Refer to ParticipantRegistry.postman_collection.json in the docs folder.
