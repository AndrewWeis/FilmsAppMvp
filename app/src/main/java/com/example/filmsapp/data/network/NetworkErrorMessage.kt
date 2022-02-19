package com.example.filmsapp.data.network

import androidx.annotation.StringRes
import com.example.filmsapp.R
import com.example.utils.ResourcesUtils

/**
 * Класс, представлющий сообщения интернет ошибки
 */
enum class NetworkErrorMessage {
    SERVER_ERROR {
        override fun getStringRes(): Int {
            return R.string.error_server
        }
    },
    NO_INTERNET_CONNECTION {
        override fun getStringRes(): Int {
            return R.string.error_no_internet_connection
        }
    },
    UNKNOWN {
        override fun getStringRes(): Int {
            return R.string.error_unknown
        }
    },
    DATA_PARSING {
        override fun getStringRes(): Int {
            return R.string.error_data_parsing
        }
    },
    SLOW_INTERNET_CONNECTION {
        override fun getStringRes(): Int {
            return R.string.error_slow_internet_connection
        }
    };

    @StringRes
    abstract fun getStringRes(): Int

    fun getMessage(): String {
        return ResourcesUtils.getString(getStringRes())
    }
}