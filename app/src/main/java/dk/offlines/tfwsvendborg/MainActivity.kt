package dk.offlines.tfwsvendborg

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.navigation.NavigationView
import dk.offlines.tfwsvendborg.ui.main.MainFragment
import dk.offlines.tfwsvendborg.ui.main.MainViewModel
import dk.offlines.tfwsvendborg.ui.main.ProfileFragment
import dk.offlines.tfwsvendborg.ui.warrMan.WarrManFragment
import kotlinx.android.synthetic.main.nav_header.view.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.draw_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val header = navigationView.getHeaderView(0)
        header.header_username.text = viewModel.getUser().username

        val toggle = ActionBarDrawerToggle(this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MainFragment()).commit()

    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, LoginActivity::class.java)
        when(item.itemId){
            R.id.nav_profile -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ProfileFragment()).commit()
            R.id.nav_warr_man -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, WarrManFragment()).commit()
            R.id.nav_logout -> {
                viewModel.removeUser()
                this.startActivity(intent)
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

}
