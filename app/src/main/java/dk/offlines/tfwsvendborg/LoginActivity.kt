package dk.offlines.tfwsvendborg

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.ContactsContract
import android.view.MenuItem
import android.widget.Switch
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import dk.offlines.tfwsvendborg.ui.main.MainFragment
import dk.offlines.tfwsvendborg.ui.main.ProfileFragment
import kotlinx.android.synthetic.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}
