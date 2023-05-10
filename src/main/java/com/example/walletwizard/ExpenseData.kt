package com.example.walletwizard

class ExpenseData{

    var id : String? = null
    var label: String? = null
    var amount:  String? = null
    var description: String? = null

    constructor( id:String?, amount : String?, label : String?, description : String? ){
        this.id = id
        this.amount = amount
        this.label = label
        this.description = description
    }
}



