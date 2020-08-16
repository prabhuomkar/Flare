package io.github.prabhuomkar.torchexpo

import android.app.DownloadManager
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import io.github.prabhuomkar.torchexpo.util.DownloadUtil

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setupNavigation()
        registerReceiver(
            DownloadUtil.broadcastReceiver,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(applicationContext).inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.aboutFragment -> {
            item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
            true
        }
        R.id.action_help -> {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(Constants.HELP_URL)
                )
            )
            true
        }
        R.id.action_contact -> {
            val emailIntent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", Constants.CONTACT_EMAIL, null
                )
            )
            try {
                startActivity(Intent.createChooser(emailIntent, "Contact TorchExpo"))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    this@MainActivity,
                    this.getString(R.string.err_no_email_client),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
