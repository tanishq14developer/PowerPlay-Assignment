package com.tanishqchawda.beertu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.tanishqchawda.beertu.adapter.BeerAdapter
import com.tanishqchawda.beertu.databinding.ActivityMainBinding
import com.tanishqchawda.beertu.utils.Utils
import com.tanishqchawda.beertu.viewModel.BeerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private  val TAG = "MainActivity"
    private val beerViewModel: BeerViewModel by viewModels()
    lateinit var beerAdapter: BeerAdapter
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            beerAdapter = BeerAdapter(this@MainActivity){
                Utils.shareOnWhatsapp(this@MainActivity,"${it.name}","${it.description}",it.imageUrl)

            }
            lifecycleScope.launch {
                beerViewModel.getBeer().collectLatest{
                    Log.e(TAG, "observeee:${it} ", )
                    Log.e(TAG, "onCreate:${it}")
                    beerAdapter.submitData(lifecycle,it)
                    rvBeerList.adapter = beerAdapter
                }

            }

        }
    }




    override fun onResume() {
        super.onResume()
        beerViewModel.getBeer()
    }
}