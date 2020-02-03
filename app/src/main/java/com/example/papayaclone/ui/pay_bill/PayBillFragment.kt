package com.example.papayaclone.ui.pay_bill

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.papayaclone.R

// Your IDE likely can auto-import these classes, but there are several
// different implementations so we list them here to disambiguate.
import android.Manifest
import android.content.Context
import android.view.*
import android.widget.Button
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider

import androidx.lifecycle.LifecycleOwner
import com.google.android.material.snackbar.Snackbar
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.android.synthetic.main.fragment_pay_bill.*

private const val PERMISSIONS_REQUEST_CODE = 10
private val PERMISSIONS_REQUIRED = arrayOf(Manifest.permission.CAMERA)


class PayBillFragment : Fragment(), LifecycleOwner {

    private lateinit var payBillViewModel: PayBillViewModel

    private lateinit var cameraProviderFuture : ListenableFuture<ProcessCameraProvider>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        payBillViewModel =
            ViewModelProviders.of(this).get(PayBillViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_pay_bill, container, false)

        val textView: TextView = root.findViewById(R.id.text_pay_bill)
        payBillViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        if (!hasPermissions(requireContext())) {
            // Request camera-related permissions
            requestPermissions(PERMISSIONS_REQUIRED, PERMISSIONS_REQUEST_CODE)
        } else {
            cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
            cameraProviderFuture.addListener(Runnable {
                val cameraProvider = cameraProviderFuture.get()
                bindPreview(cameraProvider)
            }, ContextCompat.getMainExecutor(requireContext()))

        }

        val captureButton: Button = root.findViewById(R.id.btn_capture)
        captureButton.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        return root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (hasPermissions(requireContext())) {
            cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
            cameraProviderFuture.addListener(Runnable {
                val cameraProvider = cameraProviderFuture.get()
                bindPreview(cameraProvider)
            }, ContextCompat.getMainExecutor(requireContext()))
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun hasPermissions(context: Context) = PERMISSIONS_REQUIRED.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    fun bindPreview(cameraProvider : ProcessCameraProvider) {
        var preview : Preview = Preview.Builder()
            .setTargetName("Preview")
            .build()

        preview.setPreviewSurfaceProvider(view_finder.getPreviewSurfaceProvider())

        var cameraSelector : CameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()

        cameraProvider.bindToLifecycle(this as LifecycleOwner, cameraSelector, preview)
    }


}