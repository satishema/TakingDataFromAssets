package com.android.funds


/**
 * Created by Satish V on 15/11/22.
 * Company : HighOnSwift pvt Ltd
 * Email Id : iamsatishema@gmail.com
 */
data class Data(
    val accountNumber: String,
    val bankLogo: String,
    val bankName: String,
    val date: Int,
    val depositAmount: Int,
    val orderId: String,
    val paymentMethod: String,
    val transactionStatus: String
)