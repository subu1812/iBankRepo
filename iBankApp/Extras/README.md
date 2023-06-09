Use Postman or similar app to construct and send requests


Create account
POST
iBank/account
{
     "acctID": "1",
	"accountName": "Vinod",
	"phoneNumber": "12345",
	"eMail": "vinod@ibank.com",
	"acctStatus": "Active"
}

POST
iBank/account
{
     "acctID": "2",
	"accountName": "Hafeez",
	"phoneNumber": "23456",
	"eMail": "hafeez@ibank.com",
	"acctStatus": "Active"
}

POST
iBank/account
{
     "acctID": "3",
	"accountName": "Gobi",
	"phoneNumber": "6543",
	"eMail": "gobi@ibank.com",
	"acctStatus": "Active"
}

PUT
iBank/account/{acctID}
{
     "acctID": "{acctID}",
	"accountName": "Gobi",
	"phoneNumber": "346787655678",
	"eMail": "gobi@ibank.com",
	"acctStatus": "InActive"
}


DELETE
/account/{acctID}

----------------------------------------------------

/POST
/iBank/accountbalance
{
     "acctID": "1",
	"balance": "10000"
}

/POST
/iBank/accountbalance
{
     "acctID": "2",
	"balance": "5000"
}


/POST
/iBank/accountbalance
{
     "acctID": "3",
	"balance": "3000"
}

/GET
/iBank/accountbalance/{acctID}


GET
/account/{acctID}/balance
/account/{acctID}

------------------------------------------------------------------
POST
iBank/account/{acctID}/addBeneficiaryAccount
{
	"id":"1",
     "acctID": "1",
	"beneficiaryAccountId": "123094355",
    "beneficiaryIFSCCode" : "IFSC00001",
    "beneficiaryName":"Sample 1",
    "status":"Active"
}


POST
iBank/account/{acctID}/addBeneficiaryAccount
{
	"id":"2",
     "acctID": "1",
	"beneficiaryAccountId": "444535466",
    "beneficiaryIFSCCode" : "IFSC00002",
    "beneficiaryName":"Sample 2",
    "status":"Inactive"
}

POST
iBank/account/{acctID}/addBeneficiaryAccount
{
	"id":"3",
     "acctID": "2",
	"beneficiaryAccountId": "75443554",
    "beneficiaryIFSCCode" : "ISFC0003",
    "beneficiaryName":"Sample 3",
    "status":"Active"
}

PUT
iBank/account/{id}/updateBeneficiaryAccount/{acctID}
{
   
	"beneficiaryAccountId": "444535466",
    "beneficiaryIFSCCode" : "IFSC00002",
    "beneficiaryName":"Sample 2",
    "status":"active"
}

Delete
iBank/account/{id}/deleteBeneficiary

-------------------------------------------------------------------------
Transactions

POST
/iBank/transactions
	{
		"id": "1",
		"acctID" :"1",
		"date" : "2022-09-18",
		"type" : "Credit",
		"amount": "1000",
		"status" : "Success",
		"remarks" : ""
	}


POST
/iBank/transactions
{
	"id": "2",
	"acctID" :"1",
	"date" : "2022-09-18",
	"type" : "Debit",
	"amount": "500",
	"status" : "Failure",
	"remarks" : ""
}

POST
/iBank/transactions
{
	"id": "3",
	"acctID" :"2",
	"date" : "2022-09-19",
	"type" : "Credit",
	"amount": "5000",
	"status" : "Success",
	"remarks" : ""
}

POST
/iBank/transactions
{
	"id": "4",
	"acctID" :"3",
	"date" : "2022-09-19",
	"type" : "Debit",
	"amount": "1000",
	"status" : "Success",
	"remarks" : ""
}

GET => acctID = 1
iBank/transactions/{acctID}
output:
[
    {
        "id": 1,
        "acctID": 1,
        "date": "2022-09-18",
        "type": "Credit",
        "amount": 1000,
        "status": "Success",
        "remarks": ""
    },
    {
        "id": 2,
        "acctID": 1,
        "date": "2022-09-18",
        "type": "Debit",
        "amount": 500,
        "status": "Failure",
        "remarks": ""
    }
]

------------------------------------------
AccountSummary -> get all details about that account, balance, transaction history

GET => acctID = 1
/iBank/account/1/accountSummary

output:
[
    {
        "acctID": 1,
        "accountName": "Vinod",
        "phoneNumber": 12345,
        "eMail": "vinod@ibank.com",
        "acctStatus": "Active"
    },
    {
        "acctID": 1,
        "balance": 10000
    },
    [
        {
            "id": 1,
            "acctID": 1,
            "date": "2022-09-18",
            "type": "Credit",
            "amount": 1000,
            "status": "Success",
            "remarks": ""
        },
        {
            "id": 2,
            "acctID": 1,
            "date": "2022-09-18",
            "type": "Debit",
            "amount": 500,
            "status": "Failure",
            "remarks": ""
        }
    ]
]




PUT
/iBank/account/{acctID}/deposit/{amount}

output:

[
    {
        "acctID": 2,
        "balance": 6000
    },
    {
        "id": 11,
        "acctID": 2,
        "date": "2023-06-08",
        "type": "Credit",
        "amount": 1000,
        "status": "Success",
        "remarks": null
    }
]


/iBank/account/{acctID}/withdraw/{amount}

output:
[
    {
        "acctID": 1,
        "balance": 9500
    },
    {
        "id": 0,
        "acctID": 1,
        "date": "2023-06-08",
        "type": "Debit",
        "amount": 500,
        "status": "Success",
        "remarks": null
    }
]

/iBank/account/{acctID}/transfer/{destAcctID}/{amount}

output:

[
    [
        {
            "acctID": 1,
            "balance": 9980
        },
        {
            "id": 14,
            "acctID": 1,
            "date": "2023-06-08",
            "type": "Debit",
            "amount": 10,
            "status": "Success",
            "remarks": null
        }
    ],
    [
        {
            "acctID": 2,
            "balance": 520
        },
        {
            "id": 15,
            "acctID": 2,
            "date": "2023-06-08",
            "type": "Credit",
            "amount": 10,
            "status": "Success",
            "remarks": null
        }
    ]
]














