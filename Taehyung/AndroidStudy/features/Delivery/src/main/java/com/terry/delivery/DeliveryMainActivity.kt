package com.terry.delivery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.terry.delivery.databinding.ActivityDeliveryMainBinding

class DeliveryMainActivity : AppCompatActivity() {

    private var binding: ActivityDeliveryMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDeliveryMainBinding.inflate(layoutInflater).also { binding ->
            this.binding = binding
        }

        setContentView(binding.root)
    }
}