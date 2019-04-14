package com.alejoestevez.gnbproducts;

import androidx.lifecycle.ViewModelProviders;
import androidx.test.filters.MediumTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.alejoestevez.gnbproducts.view.adapter.ProductAdapter;
import com.alejoestevez.gnbproducts.view.ui.fragments.ProductListFragment;
import com.alejoestevez.gnbproducts.view.ui.activities.MainActivity;
import com.alejoestevez.gnbproducts.viewmodels.TransactionViewModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import org.mockito.Mock;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class MainActivityJavaTest {

    @Mock
    private TransactionViewModel productViewModel;

    @Mock
    private ProductAdapter productAdapter;

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureFrameLayoutIsPresent() throws Exception {

        MainActivity activity = rule.getActivity();
        FrameLayout viewById = activity.findViewById(R.id.fragment_container);
        assertThat(viewById, instanceOf(FrameLayout.class));

        List<Fragment> fragmentList = activity.getSupportFragmentManager().getFragments();
        assertEquals(fragmentList.size(), 1);
        Fragment fragment = (Fragment) fragmentList.get(0);
        assertThat(fragmentList.get(0), instanceOf(ProductListFragment.class));
        fragment = (ProductListFragment) fragment;

        // fragment testing
        View view = fragment.getView();
        View recyclerView = view.findViewById(R.id.product_list);
        assertThat(recyclerView, instanceOf(RecyclerView.class));
        assertEquals(view.findViewById(R.id.loading_products).getVisibility(), View.VISIBLE);
        recyclerView = (RecyclerView) recyclerView;

        productViewModel = ViewModelProviders.of(activity, new TransactionViewModel.Factory(activity.getApplication()))
                .get(TransactionViewModel.class);

        assertEquals(view.findViewById(R.id.loading_products).getVisibility(), View.VISIBLE);
        productAdapter = (ProductAdapter) ((RecyclerView) recyclerView).getAdapter();

        int count = productAdapter.getItemCount();
        assertThat(count, greaterThan(0));

    }
}
