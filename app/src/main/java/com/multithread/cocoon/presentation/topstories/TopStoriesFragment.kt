package com.multithread.cocoon.presentation.topstories

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.multithread.cocoon.base.ui.ViewModelErrorSuccessFragment
import com.multithread.cocoon.data.model.dto.TopStoryDTO
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import com.multithread.cocoon.extension.show
import com.multithread.cocoon.presentation.TopStoriesAdapterView
import com.multithread.cocoon.presentation.main.DetailActivity
import kotlinx.android.synthetic.main.fragment_top_stories.*
import kotlinx.android.synthetic.main.item_story.*
import kotlinx.coroutines.FlowPreview

class TopStoriesFragment :
    ViewModelErrorSuccessFragment<TopStoriesState, TopStoriesEvent, TopStoriesViewModel>(),
    SwipeRefreshLayout.OnRefreshListener {

    private val storiesAdapter by lazy {
        TopStoriesAdapterView(storyCallback, imageLoader)
    }

    private val storyCallback: (item: TopStoryDomainEntity.Result) -> Unit = {
        startActivity(DetailActivity.newInstance(requireContext(), it))
    }

    override fun getViewModelClass(): Class<TopStoriesViewModel> = TopStoriesViewModel::class.java

    override val contentResourceId: Int = com.multithread.cocoon.R.layout.fragment_top_stories

    @FlowPreview
    override fun initView() {
        super.initView()
        topStoriesSwipeRefresh.setOnRefreshListener(this)
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
                onDataReceived(state.data)
            }
            else -> {
                onNoData()
            }
        }
    }

    private fun onDataReceived(data: TopStoriesState.Data.TopStories) {
        topStoriesList.show(true)
        topStoriesSwipeRefresh.isRefreshing = false
        storiesAdapter.itemList = data.story.results
    }

    private fun onNoData() {
        topStoriesSwipeRefresh.isRefreshing = false
        topStoriesList.show(false)

    }

    @FlowPreview
    override fun onRefresh() {
        topStoriesSwipeRefresh.isRefreshing = false
        viewModel.handleEvent(TopStoriesEvent.GetTopStories)
    }

    companion object {
        fun newInstance() = TopStoriesFragment()
    }

}