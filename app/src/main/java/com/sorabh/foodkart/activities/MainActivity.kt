package com.sorabh.foodkart.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sorabh.foodkart.R
import com.sorabh.foodkart.databinding.ActivityMainBinding
import com.sorabh.foodkart.fragments.favorite.FavoriteFragment
import com.sorabh.foodkart.fragments.home.HomeFragment
import com.sorabh.foodkart.fragments.user.UserFragment

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        //adding data into toolbar
        with(mainBinding.toolBar) {
            supportActionBar
            actionBar
            // working with option menu
            inflateMenu(R.menu.option_menu)
            setOnMenuItemClickListener(androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {
                when (it.itemId) {
                    R.id.question -> {
                        Toast.makeText(this@MainActivity, "Frequently asked Questions!", Toast.LENGTH_LONG).show()
                    }
                    R.id.log_out -> {
                        Toast.makeText(this@MainActivity, "you logout successfully!", Toast.LENGTH_LONG).show()
                        logout()
                    }
                }
                return@OnMenuItemClickListener false
            })
        }

        //working with bottom navigation
        with(mainBinding.bottomBar) {
            this.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.bottom_menu_home -> {
                        it.isChecked = true
                        changeFragment(HomeFragment())
                    }
                    R.id.bottom_menu_user -> {
                        it.isChecked = true
                        val fragment = UserFragment()
                        fragment.arguments = getUserBundle()
                        changeFragment(fragment)
                    }
                    R.id.bottom_menu_favorite -> {
                        it.isChecked = true
                        changeFragment(FavoriteFragment())
                    }
                }
                return@setOnItemSelectedListener false
            }
        }

        //default fragment that will display
        val userFragment = UserFragment()
        userFragment.arguments = getUserBundle()
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
            .replace(mainBinding.frameLayout.id, userFragment)
            .addToBackStack("userFragment")
            .commit()
    }

    //transact between fragment
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
            .replace(mainBinding.frameLayout.id, fragment)
            .addToBackStack(fragment.toString())
            .commit()
    }

    private fun getUserBundle(): Bundle {
        val bundle = Bundle()
        val preference = getSharedPreferences("foodKartLogin", MODE_PRIVATE)
        bundle.putString("name", preference.getString("name", "name"))
        bundle.putString("email", preference.getString("email", "email"))
        bundle.putString("phone", preference.getString("phone", "phone"))
        bundle.putString("password", preference.getString("password", "password"))
        return bundle
    }
    private  fun logout(){
        val sharedPreferences = getSharedPreferences("foodKartLogin", MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}

//{
//    "data": {
//    "success": true,
//    "data": [
//    {
//        "id": "1",
//        "name": "Pind Tadka",
//        "rating": "4.1",
//        "cost_for_one": "280",
//        "image_url": "https://images.pexels.com/photos/1640777/pexels-photo-1640777.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
//    },
//    {
//        "id": "2",
//        "name": "Garbar Burgers",
//        "rating": "4.6",
//        "cost_for_one": "200",
//        "image_url": "https://images.pexels.com/photos/1639565/pexels-photo-1639565.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
//    },
//    {
//        "id": "3",
//        "name": "Baco Tell",
//        "rating": "3.4",
//        "cost_for_one": "300",
//        "image_url": "https://images.pexels.com/photos/674578/pexels-photo-674578.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
//    },
//    {
//        "id": "4",
//        "name": "Heera Mahal",
//        "rating": "4.2",
//        "cost_for_one": "300",
//        "image_url": "https://images.pexels.com/photos/1300972/pexels-photo-1300972.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
//    },
//    {
//        "id": "5",
//        "name": "Smokin' Chik",
//        "rating": "4.0",
//        "cost_for_one": "250",
//        "image_url": "https://images.pexels.com/photos/265393/pexels-photo-265393.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
//    },
//    {
//        "id": "6",
//        "name": "Swirley's Shack",
//        "rating": "3.8",
//        "cost_for_one": "400",
//        "image_url": "https://images.pexels.com/photos/699544/pexels-photo-699544.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
//    },
//    {
//        "id": "7",
//        "name": "Dominoe's bread",
//        "rating": "3.6",
//        "cost_for_one": "200",
//        "image_url": "https://images.pexels.com/photos/905847/pexels-photo-905847.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
//    },
//    {
//        "id": "8",
//        "name": "Everything but Food",
//        "rating": "3.2",
//        "cost_for_one": "150",
//        "image_url": "https://images.pexels.com/photos/5938/food-salad-healthy-lunch.jpg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
//    },
//    {
//        "id": "9",
//        "name": "LFC",
//        "rating": "4.0",
//        "cost_for_one": "200",
//        "image_url": "https://images.pexels.com/photos/60616/fried-chicken-chicken-fried-crunchy-60616.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
//    },
//    {
//        "id": "10",
//        "name": "Central Terk",
//        "rating": "5.0",
//        "cost_for_one": "300",
//        "image_url": "https://images.pexels.com/photos/434213/pexels-photo-434213.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
//    },
//    {
//        "id": "11",
//        "name": "Mitti ke Sandwiches",
//        "rating": "4.0",
//        "cost_for_one": "250",
//        "image_url": "https://images.pexels.com/photos/1600711/pexels-photo-1600711.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
//    },
//    {
//        "id": "13",
//        "name": "Pizza Put",
//        "rating": "4.4",
//        "cost_for_one": "350",
//        "image_url": "https://images.pexels.com/photos/724216/pexels-photo-724216.jpeg?auto=compress&cs=tinysrgb&h=650&w=940"
//    },
//    {
//        "id": "14",
//        "name": "Burger Jack",
//        "rating": "3.7",
//        "cost_for_one": "250",
//        "image_url": "https://images.pexels.com/photos/983297/pexels-photo-983297.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
//    },
//    {
//        "id": "15",
//        "name": "Rotten Tomatoes",
//        "rating": "3.2",
//        "cost_for_one": "100",
//        "image_url": "https://images.pexels.com/photos/428301/pexels-photo-428301.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
//    },
//    {
//        "id": "16",
//        "name": "NcDonald's",
//        "rating": "3.6",
//        "cost_for_one": "150",
//        "image_url": "https://images.pexels.com/photos/551991/pexels-photo-551991.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
//    },
//    {
//        "id": "17",
//        "name": "Askin' Poppins",
//        "rating": "4.1",
//        "cost_for_one": "300",
//        "image_url": "https://images.pexels.com/photos/3631/summer-dessert-sweet-ice-cream.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500"
//    },
//    {
//        "id": "18",
//        "name": "Baasa Menu",
//        "rating": "3.4",
//        "cost_for_one": "200",
//        "image_url": "https://images.pexels.com/photos/264537/pexels-photo-264537.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
//    }
//    ]
//}
//}
