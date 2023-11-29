import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simpalaapps.R
import com.example.simpalaapps.model.ReportEntity
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.GoogleMap
//import com.google.android.gms.maps.OnMapReadyCallback
//import com.google.android.gms.maps.SupportMapFragment
//import com.google.android.gms.maps.model.LatLng
//import com.google.android.gms.maps.model.MarkerOptions

//class MapFragment : Fragment(), OnMapReadyCallback {
class MapFragment : Fragment() {

//    private lateinit var googleMap: GoogleMap
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_map, container, false)
//
//        // Inisialisasi SupportMapFragment
//        val mapFragment =
//            childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
//        mapFragment.getMapAsync(this)
//
//        return view
//    }
//
//    override fun onMapReady(map: GoogleMap) {
//        googleMap = map
//
//        // Ambil laporan dari database atau repository
//        val reports: List<ReportEntity> = getReportsFromDatabase()
//
//        // Tampilkan titik lokasi sampah dari laporan
//        showReportLocations(reports)
//    }
//
//    private fun getReportsFromDatabase(): List<ReportEntity> {
//        // Implementasikan metode untuk mengambil laporan dari database menggunakan Room
//        // Misalnya, reportDao.getAllReports()
//        return emptyList() // Gantilah dengan hasil yang sesuai
//    }
//
//    private fun showReportLocations(reports: List<ReportEntity>) {
//        for (report in reports) {
//            val location = LatLng(report.latitude, report.longitude)
//            googleMap.addMarker(MarkerOptions().position(location).title(report.reportType))
//        }
//
//        // Fokuskan kamera pada titik pertama (jika ada)
//        if (reports.isNotEmpty()) {
//            val firstLocation = LatLng(reports[0].latitude, reports[0].longitude)
//            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLocation, 12f))
//        }
//    }
}
