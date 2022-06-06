package com.example.finalstraw

import com.google.firebase.database.Exclude

data class Details(
                    @get:Exclude
                    var id: String? = null,
                    var consigner: String? = null,
                    var consignee: String? = null,
                    var number: String? =null,
                    var destination: String? = null,
                    var pickup: String? = null,
                    )
