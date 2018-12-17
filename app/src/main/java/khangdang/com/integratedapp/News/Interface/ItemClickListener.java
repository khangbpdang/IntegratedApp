package khangdang.com.integratedapp.News.Interface;

import android.view.View;

/**
 * The purpose of this class is to be able to load the news articles when clicked through the Web Browser
 * while using the RecyclerView
 */

public interface ItemClickListener {
    void onClick(View view, int position, boolean isLongClick);
}
