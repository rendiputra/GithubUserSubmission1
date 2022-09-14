package com.rendiputra.githubuser

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.rendiputra.githubuser.adapter.ListDetailAdapter
import com.rendiputra.githubuser.data.DetailItem
import com.rendiputra.githubuser.data.User
import de.hdodenhof.circleimageview.CircleImageView

class DetailActivity : AppCompatActivity() {

    private lateinit var user: User

    private lateinit var listDetailAdapter: ListDetailAdapter

    private val listItem = ArrayList<DetailItem>()

    private lateinit var rvDetailItem: RecyclerView
    private lateinit var ivAvatar: CircleImageView
    private lateinit var tvName: TextView
    private lateinit var tvUsername: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        user = intent.getParcelableExtra<User>("extra_user") as User

        getListDetailItem()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        listDetailAdapter = ListDetailAdapter(listItem)

        setupView()
        setupRecyclerView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun getListDetailItem() {
        listItem.add(DetailItem(R.drawable.ic_baseline_people_24, getString(R.string.format_followers, user.followers)))
        listItem.add(DetailItem(R.drawable.ic_baseline_following_24, getString(R.string.format_following, user.following)))
        listItem.add(DetailItem(R.drawable.ic_baseline_company_24, user.company))
        listItem.add(DetailItem(R.drawable.ic_baseline_book_24, user.repository))
        listItem.add(DetailItem(R.drawable.ic_baseline_location_on_24, user.location))
    }

    private fun setupView() {
        rvDetailItem = findViewById(R.id.rv_detail_item)
        ivAvatar = findViewById(R.id.iv_avatar)
        tvName = findViewById(R.id.tv_name)
        tvUsername = findViewById(R.id.tv_username)

        ivAvatar.setImageResource(user.avatar)
        tvName.text = user.name
        tvUsername.text = user.username
    }

    private fun setupRecyclerView() {
        rvDetailItem.adapter = listDetailAdapter
    }
}