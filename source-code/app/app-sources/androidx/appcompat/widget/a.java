package androidx.appcompat.widget;

import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public final class a implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ SearchView f239b;

    public a(SearchView searchView) {
        this.f239b = searchView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SearchView searchView = this.f239b;
        ImageView imageView = searchView.f200u;
        SearchView.SearchAutoComplete searchAutoComplete = searchView.f196q;
        if (view == imageView) {
            searchView.x(false);
            searchAutoComplete.requestFocus();
            searchAutoComplete.setImeVisibility(true);
            View.OnClickListener onClickListener = searchView.M;
            if (onClickListener != null) {
                onClickListener.onClick(searchView);
                return;
            }
            return;
        }
        if (view == searchView.f202w) {
            searchView.n();
            return;
        }
        if (view == searchView.f201v) {
            searchView.r();
            return;
        }
        if (view != searchView.f203x) {
            if (view == searchAutoComplete) {
                searchView.m();
                return;
            }
            return;
        }
        SearchableInfo searchableInfo = searchView.f192c0;
        if (searchableInfo == null) {
            return;
        }
        try {
            if (!searchableInfo.getVoiceSearchLaunchWebSearch()) {
                if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                    searchView.getContext().startActivity(searchView.l(searchView.J, searchableInfo));
                }
            } else {
                Intent intent = new Intent(searchView.I);
                ComponentName searchActivity = searchableInfo.getSearchActivity();
                intent.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
                searchView.getContext().startActivity(intent);
            }
        } catch (ActivityNotFoundException unused) {
            Log.w("SearchView", "Could not find voice search activity");
        }
    }
}
