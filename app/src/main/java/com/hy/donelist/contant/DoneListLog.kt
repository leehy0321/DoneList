package com.hy.donelist.contant

import android.util.Log

private const val TAG = "DoneListApp"

class DoneListLog {
    fun LogD(content: String) {
        Log.d(TAG, content);
    }

    fun LogI(content: String) {
        Log.i(TAG, content);
    }

    fun LogV(content: String) {
        Log.v(TAG, content);
    }

    fun LogE(content: String) {
        Log.e(TAG, content);
    }

}