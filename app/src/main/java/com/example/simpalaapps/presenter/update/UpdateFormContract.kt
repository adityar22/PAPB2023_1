package com.example.simpalaapps.presenter.update

import com.example.simpalaapps.model.ReportEntity

// UpdateFormContract.kt

interface UpdateFormContract {

    interface View {
        // Add methods as needed
        fun onUpdateClicked()
    }

    interface Presenter {
        fun onUpdateClicked(updatedReport: ReportEntity)
    }
}
