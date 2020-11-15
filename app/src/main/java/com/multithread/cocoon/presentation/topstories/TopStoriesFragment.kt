package com.multithread.cocoon.presentation.topstories

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.multithread.cocoon.base.ui.ViewModelErrorSuccessFragment
import com.multithread.cocoon.data.model.dto.TopStoryDTO
import com.multithread.cocoon.extension.show
import com.multithread.cocoon.presentation.TopStoriesAdapterView
import kotlinx.android.synthetic.main.fragment_top_stories.*
import kotlinx.coroutines.FlowPreview

class TopStoriesFragment :
    ViewModelErrorSuccessFragment<TopStoriesState, TopStoriesEvent, TopStoriesViewModel>(),
    SwipeRefreshLayout.OnRefreshListener {

    private val storiesAdapter by lazy {
        TopStoriesAdapterView(storyCallback, imageLoader)
    }

    private val storyCallback: (item: TopStoryDTO.Result) -> Unit = {
        TODO("Launch detail screen")
    }

    override fun getViewModelClass(): Class<TopStoriesViewModel> = TopStoriesViewModel::class.java

    override val contentResourceId: Int = com.multithread.cocoon.R.layout.fragment_top_stories

    @FlowPreview
    override fun initView() {
        super.initView()
        topStoriesList.apply {
            adapter = storiesAdapter
            setHasFixedSize(true)
        }
        viewModel.handleEvent(TopStoriesEvent.GetTopStories)
    }


    override fun showLoadingSpinner(loading: Boolean) {
        topStoriesProgressBar.show(loading)
    }

    override fun renderState(state: TopStoriesState) {
        super.renderState(state)
        when(state.data){
            is TopStoriesState.Data.TopStories -> {

            }
            else -> {
                onNoData()
            }
        }
    }

    private fun onNoData() {

    }

    @FlowPreview
    override fun onRefresh() {
        viewModel.handleEvent(TopStoriesEvent.GetTopStories)
        topStoriesSwipeRefresh.isRefreshing = false
    }

}