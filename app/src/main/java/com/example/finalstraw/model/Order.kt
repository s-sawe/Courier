package com.example.finalstraw.model

import com.google.firebase.database.Exclude

data class Order(    @get:Exclude
                     var id: String? = null,
                     var uid: String? = null,
                     var consigner: String? = null,
                     var consignee: String? = null,
                     var idnumber: String? =null,
                     var destination: String? = null,
                     var pickup: String? = null,
                     var status: String? = null,)
