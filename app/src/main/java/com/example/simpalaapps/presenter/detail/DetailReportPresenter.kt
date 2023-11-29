package com.example.simpalaapps.presenter.detail


class DetailReportPresenter(
    private val view: DetailReportContract.View,
    private val repository: ReportRepository // Assume you have a repository to handle data access
) : DetailReportContract.Presenter {

    override fun onViewCreated(reportId: Int) {
        // Retrieve report details from the repository based on the report ID
        val report = repository.getReportById(reportId)
        view.showReportDetails(report)
    }
}
