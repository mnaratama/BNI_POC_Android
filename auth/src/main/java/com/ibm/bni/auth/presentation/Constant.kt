package com.ibm.bni.auth.presentation

object Constant {

    const val GENERATE_OTP = "api/v1/generateotp"

    const val VALIDATE_OTP = "api/v1/validateotp"

    const val VALIDATE_CREDENTIALS = "https://productservice-mavipoc-ps.apps.mavipoc-pb.duh8.p1.openshiftapps.com/api/v1/verifyCredentials"

    const val GET_RECEIVERS = "https://paymentservice-mavipoc-payment-service.apps.mavipoc-pb.duh8.p1.openshiftapps.com/api/v1/receiver/all?cif=CIF-00000"

    const val SHARE_PREF_APP_NAME = "bni"
    const val USER_ID = "userId"
    const val SECRET_USER_ID = "xmptlyz"

    const val BIOMETRIC_ID = "biometric_user_id"
    const val ACCOUNT_NO = "accountNo"
    const val CIF_ACCOUNT = "cifAccount"
    const val ACCOUNT_NAME ="accountname"
}