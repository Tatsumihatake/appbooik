package com.example.appbook2

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.LocationServices
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class SearchFragment : Fragment() {
    private lateinit var map: MapView
    private val reqLoc = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { p ->
        if (p[Manifest.permission.ACCESS_FINE_LOCATION] == true) setupMap()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        context?.let { Configuration.getInstance().load(it, PreferenceManager.getDefaultSharedPreferences(it)) }
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        map = view.findViewById(R.id.mapView)
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true)

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) setupMap()
        else reqLoc.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
        return view
    }

    @SuppressLint("MissingPermission")
    private fun setupMap() {
        val overlay = MyLocationNewOverlay(GpsMyLocationProvider(requireContext()), map)
        overlay.enableMyLocation()
        map.overlays.add(overlay)

        LocationServices.getFusedLocationProviderClient(requireActivity()).lastLocation.addOnSuccessListener { loc ->
            if (loc != null) {
                val pt = GeoPoint(loc.latitude, loc.longitude)
                map.controller.setZoom(15.0)
                map.controller.setCenter(pt)
                val m = Marker(map).apply { position = GeoPoint(pt.latitude+0.002, pt.longitude+0.002); title = "Perpustakaan (Demo)" }
                map.overlays.add(m)
            }
        }
    }
    override fun onResume() { super.onResume(); map.onResume() }
    override fun onPause() { super.onPause(); map.onPause() }
}