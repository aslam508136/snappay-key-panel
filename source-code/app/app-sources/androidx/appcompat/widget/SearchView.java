package androidx.appcompat.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import androidx.lifecycle.i;
import androidx.security.crypto.MasterKey;
import com.snapay.app.R;
import h.d;
import j.d2;
import j.e2;
import j.f2;
import j.g2;
import j.h2;
import j.i2;
import j.j2;
import j.k2;
import j.l2;
import j.n1;
import j.n2;
import j.o2;
import j.p1;
import j.q2;
import j.r;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SearchView extends n1 implements d {

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public static final m0.a f189h0;
    public final Rect A;
    public final Rect B;
    public final int[] C;
    public final int[] D;
    public final ImageView E;
    public final Drawable F;
    public final int G;
    public final int H;
    public final Intent I;
    public final Intent J;
    public final CharSequence K;
    public View.OnFocusChangeListener L;
    public View.OnClickListener M;
    public boolean N;
    public boolean O;
    public b0.b P;
    public boolean Q;
    public CharSequence R;
    public boolean S;
    public boolean T;
    public int U;
    public boolean V;
    public CharSequence W;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public boolean f190a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public int f191b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public SearchableInfo f192c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public Bundle f193d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final e2 f194e0;
    public final e2 f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final WeakHashMap f195g0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final SearchAutoComplete f196q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final View f197r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final View f198s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final View f199t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final ImageView f200u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final ImageView f201v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final ImageView f202w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final ImageView f203x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final View f204y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public o2 f205z;

    public static class SearchAutoComplete extends r {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f206e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public SearchView f207f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f208g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final c f209h;

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f209h = new c(this);
            this.f206e = getThreshold();
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i2 = configuration.screenWidthDp;
            int i3 = configuration.screenHeightDp;
            if (i2 >= 960 && i3 >= 720 && configuration.orientation == 2) {
                return MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE;
            }
            if (i2 < 600) {
                return (i2 < 640 || i3 < 480) ? 160 : 192;
            }
            return 192;
        }

        public final void a() {
            if (Build.VERSION.SDK_INT >= 29) {
                setInputMethodMode(1);
                if (enoughToFilter()) {
                    showDropDown();
                    return;
                }
                return;
            }
            m0.a aVar = SearchView.f189h0;
            aVar.getClass();
            m0.a.v();
            Object obj = aVar.f1644c;
            if (((Method) obj) != null) {
                try {
                    ((Method) obj).invoke(this, Boolean.TRUE);
                } catch (Exception unused) {
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public final boolean enoughToFilter() {
            return this.f206e <= 0 || super.enoughToFilter();
        }

        @Override // j.r, android.widget.TextView, android.view.View
        public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.f208g) {
                c cVar = this.f209h;
                removeCallbacks(cVar);
                post(cVar);
            }
            return inputConnectionOnCreateInputConnection;
        }

        @Override // android.view.View
        public final void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public final void onFocusChanged(boolean z2, int i2, Rect rect) {
            super.onFocusChanged(z2, i2, rect);
            SearchView searchView = this.f207f;
            searchView.x(searchView.O);
            searchView.post(searchView.f194e0);
            if (searchView.f196q.hasFocus()) {
                searchView.m();
            }
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public final boolean onKeyPreIme(int i2, KeyEvent keyEvent) {
            if (i2 == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                }
                if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.f207f.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i2, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public final void onWindowFocusChanged(boolean z2) {
            super.onWindowFocusChanged(z2);
            if (z2 && this.f207f.hasFocus() && getVisibility() == 0) {
                this.f208g = true;
                Context context = getContext();
                m0.a aVar = SearchView.f189h0;
                if (context.getResources().getConfiguration().orientation == 2) {
                    a();
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public final void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        public final void replaceText(CharSequence charSequence) {
        }

        public void setImeVisibility(boolean z2) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            c cVar = this.f209h;
            if (!z2) {
                this.f208g = false;
                removeCallbacks(cVar);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (!inputMethodManager.isActive(this)) {
                    this.f208g = true;
                    return;
                }
                this.f208g = false;
                removeCallbacks(cVar);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        public void setSearchView(SearchView searchView) {
            this.f207f = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i2) {
            super.setThreshold(i2);
            this.f206e = i2;
        }
    }

    static {
        f189h0 = Build.VERSION.SDK_INT < 29 ? new m0.a() : null;
    }

    public SearchView(Context context) {
        this(context, null);
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_width);
    }

    private void setQuery(CharSequence charSequence) {
        SearchAutoComplete searchAutoComplete = this.f196q;
        searchAutoComplete.setText(charSequence);
        searchAutoComplete.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    @Override // h.d
    public final void b() {
        if (this.f190a0) {
            return;
        }
        this.f190a0 = true;
        SearchAutoComplete searchAutoComplete = this.f196q;
        int imeOptions = searchAutoComplete.getImeOptions();
        this.f191b0 = imeOptions;
        searchAutoComplete.setImeOptions(imeOptions | 33554432);
        searchAutoComplete.setText("");
        setIconified(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void clearFocus() {
        this.T = true;
        super.clearFocus();
        SearchAutoComplete searchAutoComplete = this.f196q;
        searchAutoComplete.clearFocus();
        searchAutoComplete.setImeVisibility(false);
        this.T = false;
    }

    @Override // h.d
    public final void d() {
        SearchAutoComplete searchAutoComplete = this.f196q;
        searchAutoComplete.setText("");
        searchAutoComplete.setSelection(searchAutoComplete.length());
        this.W = "";
        clearFocus();
        x(true);
        searchAutoComplete.setImeOptions(this.f191b0);
        this.f190a0 = false;
    }

    public int getImeOptions() {
        return this.f196q.getImeOptions();
    }

    public int getInputType() {
        return this.f196q.getInputType();
    }

    public int getMaxWidth() {
        return this.U;
    }

    public CharSequence getQuery() {
        return this.f196q.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.R;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.f192c0;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.K : getContext().getText(this.f192c0.getHintId());
    }

    public int getSuggestionCommitIconResId() {
        return this.H;
    }

    public int getSuggestionRowLayout() {
        return this.G;
    }

    public b0.b getSuggestionsAdapter() {
        return this.P;
    }

    public final Intent k(String str, Uri uri, String str2, String str3) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.W);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.f193d0;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        intent.setComponent(this.f192c0.getSearchActivity());
        return intent;
    }

    public final Intent l(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1073741824);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f193d0;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        Resources resources = getResources();
        String string = searchableInfo.getVoiceLanguageModeId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageModeId()) : "free_form";
        String string2 = searchableInfo.getVoicePromptTextId() != 0 ? resources.getString(searchableInfo.getVoicePromptTextId()) : null;
        String string3 = searchableInfo.getVoiceLanguageId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageId()) : null;
        int voiceMaxResults = searchableInfo.getVoiceMaxResults() != 0 ? searchableInfo.getVoiceMaxResults() : 1;
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", string);
        intent3.putExtra("android.speech.extra.PROMPT", string2);
        intent3.putExtra("android.speech.extra.LANGUAGE", string3);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", voiceMaxResults);
        intent3.putExtra("calling_package", searchActivity != null ? searchActivity.flattenToShortString() : null);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    public final void m() {
        int i2 = Build.VERSION.SDK_INT;
        SearchAutoComplete searchAutoComplete = this.f196q;
        if (i2 >= 29) {
            searchAutoComplete.refreshAutoCompleteResults();
            return;
        }
        m0.a aVar = f189h0;
        aVar.getClass();
        m0.a.v();
        Object obj = aVar.f1642a;
        if (((Method) obj) != null) {
            try {
                ((Method) obj).invoke(searchAutoComplete, new Object[0]);
            } catch (Exception unused) {
            }
        }
        aVar.getClass();
        m0.a.v();
        Object obj2 = aVar.f1643b;
        if (((Method) obj2) != null) {
            try {
                ((Method) obj2).invoke(searchAutoComplete, new Object[0]);
            } catch (Exception unused2) {
            }
        }
    }

    public final void n() {
        SearchAutoComplete searchAutoComplete = this.f196q;
        if (!TextUtils.isEmpty(searchAutoComplete.getText())) {
            searchAutoComplete.setText("");
            searchAutoComplete.requestFocus();
            searchAutoComplete.setImeVisibility(true);
        } else if (this.N) {
            clearFocus();
            x(true);
        }
    }

    public final void o(int i2) {
        int position;
        String strH;
        Cursor cursor = this.P.f475d;
        if (cursor != null && cursor.moveToPosition(i2)) {
            Intent intentK = null;
            try {
                int i3 = q2.f1363y;
                String strH2 = q2.h(cursor, cursor.getColumnIndex("suggest_intent_action"));
                if (strH2 == null) {
                    strH2 = this.f192c0.getSuggestIntentAction();
                }
                if (strH2 == null) {
                    strH2 = "android.intent.action.SEARCH";
                }
                String strH3 = q2.h(cursor, cursor.getColumnIndex("suggest_intent_data"));
                if (strH3 == null) {
                    strH3 = this.f192c0.getSuggestIntentData();
                }
                if (strH3 != null && (strH = q2.h(cursor, cursor.getColumnIndex("suggest_intent_data_id"))) != null) {
                    strH3 = strH3 + "/" + Uri.encode(strH);
                }
                intentK = k(strH2, strH3 == null ? null : Uri.parse(strH3), q2.h(cursor, cursor.getColumnIndex("suggest_intent_extra_data")), q2.h(cursor, cursor.getColumnIndex("suggest_intent_query")));
            } catch (RuntimeException e2) {
                try {
                    position = cursor.getPosition();
                } catch (RuntimeException unused) {
                    position = -1;
                }
                Log.w("SearchView", "Search suggestions cursor at row " + position + " returned exception.", e2);
            }
            if (intentK != null) {
                try {
                    getContext().startActivity(intentK);
                } catch (RuntimeException e3) {
                    Log.e("SearchView", "Failed launch activity: " + intentK, e3);
                }
            }
        }
        SearchAutoComplete searchAutoComplete = this.f196q;
        searchAutoComplete.setImeVisibility(false);
        searchAutoComplete.dismissDropDown();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.f194e0);
        post(this.f0);
        super.onDetachedFromWindow();
    }

    @Override // j.n1, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        if (z2) {
            SearchAutoComplete searchAutoComplete = this.f196q;
            int[] iArr = this.C;
            searchAutoComplete.getLocationInWindow(iArr);
            int[] iArr2 = this.D;
            getLocationInWindow(iArr2);
            int i6 = iArr[1] - iArr2[1];
            int i7 = iArr[0] - iArr2[0];
            int width = searchAutoComplete.getWidth() + i7;
            int height = searchAutoComplete.getHeight() + i6;
            Rect rect = this.A;
            rect.set(i7, i6, width, height);
            int i8 = rect.left;
            int i9 = rect.right;
            int i10 = i5 - i3;
            Rect rect2 = this.B;
            rect2.set(i8, 0, i9, i10);
            o2 o2Var = this.f205z;
            if (o2Var == null) {
                o2 o2Var2 = new o2(rect2, rect, searchAutoComplete);
                this.f205z = o2Var2;
                setTouchDelegate(o2Var2);
            } else {
                o2Var.f1342b.set(rect2);
                Rect rect3 = o2Var.f1344d;
                rect3.set(rect2);
                int i11 = -o2Var.f1345e;
                rect3.inset(i11, i11);
                o2Var.f1343c.set(rect);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0041 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:27:0x0044  */
    /* JADX WARN: Code duplicated, block: B:28:0x0049  */
    @Override // j.n1, android.view.View
    public final void onMeasure(int i2, int i3) {
        int preferredWidth;
        int mode;
        int size;
        if (this.O) {
            super.onMeasure(i2, i3);
            return;
        }
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode2 != Integer.MIN_VALUE) {
            if (mode2 == 0) {
                size2 = this.U;
                if (size2 <= 0) {
                    size2 = getPreferredWidth();
                }
            } else if (mode2 == 1073741824 && (preferredWidth = this.U) > 0) {
            }
            mode = View.MeasureSpec.getMode(i3);
            size = View.MeasureSpec.getSize(i3);
            if (mode != Integer.MIN_VALUE) {
                size = Math.min(getPreferredHeight(), size);
            } else if (mode == 0) {
                size = getPreferredHeight();
            }
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(size2, 1073741824), View.MeasureSpec.makeMeasureSpec(size, 1073741824));
        }
        preferredWidth = this.U;
        if (preferredWidth <= 0) {
            preferredWidth = getPreferredWidth();
        }
        size2 = Math.min(preferredWidth, size2);
        mode = View.MeasureSpec.getMode(i3);
        size = View.MeasureSpec.getSize(i3);
        if (mode != Integer.MIN_VALUE) {
            size = Math.min(getPreferredHeight(), size);
        } else if (mode == 0) {
            size = getPreferredHeight();
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size2, 1073741824), View.MeasureSpec.makeMeasureSpec(size, 1073741824));
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof n2)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        n2 n2Var = (n2) parcelable;
        super.onRestoreInstanceState(n2Var.f508a);
        x(n2Var.f1337c);
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        n2 n2Var = new n2(super.onSaveInstanceState());
        n2Var.f1337c = this.O;
        return n2Var;
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        post(this.f194e0);
    }

    public final void p(int i2) {
        String strC;
        Editable text = this.f196q.getText();
        Cursor cursor = this.P.f475d;
        if (cursor == null) {
            return;
        }
        if (!cursor.moveToPosition(i2) || (strC = this.P.c(cursor)) == null) {
            setQuery(text);
        } else {
            setQuery(strC);
        }
    }

    public final void q(CharSequence charSequence) {
        setQuery(charSequence);
    }

    public final void r() {
        SearchAutoComplete searchAutoComplete = this.f196q;
        Editable text = searchAutoComplete.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        if (this.f192c0 != null) {
            getContext().startActivity(k("android.intent.action.SEARCH", null, null, text.toString()));
        }
        searchAutoComplete.setImeVisibility(false);
        searchAutoComplete.dismissDropDown();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean requestFocus(int i2, Rect rect) {
        if (this.T || !isFocusable()) {
            return false;
        }
        if (this.O) {
            return super.requestFocus(i2, rect);
        }
        boolean zRequestFocus = this.f196q.requestFocus(i2, rect);
        if (zRequestFocus) {
            x(false);
        }
        return zRequestFocus;
    }

    public final void s() {
        boolean z2 = true;
        boolean z3 = !TextUtils.isEmpty(this.f196q.getText());
        if (!z3 && (!this.N || this.f190a0)) {
            z2 = false;
        }
        int i2 = z2 ? 0 : 8;
        ImageView imageView = this.f202w;
        imageView.setVisibility(i2);
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            drawable.setState(z3 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    public void setAppSearchData(Bundle bundle) {
        this.f193d0 = bundle;
    }

    public void setIconified(boolean z2) {
        if (z2) {
            n();
            return;
        }
        x(false);
        SearchAutoComplete searchAutoComplete = this.f196q;
        searchAutoComplete.requestFocus();
        searchAutoComplete.setImeVisibility(true);
        View.OnClickListener onClickListener = this.M;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public void setIconifiedByDefault(boolean z2) {
        if (this.N == z2) {
            return;
        }
        this.N = z2;
        x(z2);
        u();
    }

    public void setImeOptions(int i2) {
        this.f196q.setImeOptions(i2);
    }

    public void setInputType(int i2) {
        this.f196q.setInputType(i2);
    }

    public void setMaxWidth(int i2) {
        this.U = i2;
        requestLayout();
    }

    public void setOnCloseListener(j2 j2Var) {
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.L = onFocusChangeListener;
    }

    public void setOnQueryTextListener(k2 k2Var) {
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.M = onClickListener;
    }

    public void setOnSuggestionListener(l2 l2Var) {
    }

    public void setQueryHint(CharSequence charSequence) {
        this.R = charSequence;
        u();
    }

    public void setQueryRefinementEnabled(boolean z2) {
        this.S = z2;
        b0.b bVar = this.P;
        if (bVar instanceof q2) {
            ((q2) bVar).f1372q = z2 ? 2 : 1;
        }
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0098  */
    public void setSearchableInfo(SearchableInfo searchableInfo) {
        boolean z2;
        this.f192c0 = searchableInfo;
        SearchAutoComplete searchAutoComplete = this.f196q;
        Intent intent = null;
        if (searchableInfo != null) {
            searchAutoComplete.setThreshold(searchableInfo.getSuggestThreshold());
            searchAutoComplete.setImeOptions(this.f192c0.getImeOptions());
            int inputType = this.f192c0.getInputType();
            if ((inputType & 15) == 1) {
                inputType &= -65537;
                if (this.f192c0.getSuggestAuthority() != null) {
                    inputType = inputType | 65536 | 524288;
                }
            }
            searchAutoComplete.setInputType(inputType);
            b0.b bVar = this.P;
            if (bVar != null) {
                bVar.b(null);
            }
            if (this.f192c0.getSuggestAuthority() != null) {
                q2 q2Var = new q2(getContext(), this, this.f192c0, this.f195g0);
                this.P = q2Var;
                searchAutoComplete.setAdapter(q2Var);
                ((q2) this.P).f1372q = this.S ? 2 : 1;
            }
            u();
        }
        SearchableInfo searchableInfo2 = this.f192c0;
        if (searchableInfo2 != null && searchableInfo2.getVoiceSearchEnabled()) {
            if (this.f192c0.getVoiceSearchLaunchWebSearch()) {
                intent = this.I;
            } else if (this.f192c0.getVoiceSearchLaunchRecognizer()) {
                intent = this.J;
            }
            z2 = (intent == null || getContext().getPackageManager().resolveActivity(intent, 65536) == null) ? false : true;
        }
        this.V = z2;
        if (z2) {
            searchAutoComplete.setPrivateImeOptions("nm");
        }
        x(this.O);
    }

    public void setSubmitButtonEnabled(boolean z2) {
        this.Q = z2;
        x(this.O);
    }

    public void setSuggestionsAdapter(b0.b bVar) {
        this.P = bVar;
        this.f196q.setAdapter(bVar);
    }

    public final void t() {
        int[] iArr = this.f196q.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.f198s.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.f199t.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void u() {
        Drawable drawable;
        CharSequence queryHint = getQueryHint();
        CharSequence charSequence = queryHint;
        if (queryHint == null) {
            charSequence = "";
        }
        boolean z2 = this.N;
        SearchAutoComplete searchAutoComplete = this.f196q;
        CharSequence charSequence2 = charSequence;
        if (z2 && (drawable = this.F) != null) {
            charSequence2 = charSequence;
            int textSize = (int) (((double) searchAutoComplete.getTextSize()) * 1.25d);
            drawable.setBounds(0, 0, textSize, textSize);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
            spannableStringBuilder.setSpan(new ImageSpan(drawable), 1, 2, 33);
            spannableStringBuilder.append(charSequence);
            charSequence2 = spannableStringBuilder;
        }
        charSequence2 = charSequence;
        searchAutoComplete.setHint(charSequence2);
    }

    public final void v() {
        int i2 = 0;
        if (!((this.Q || this.V) && !this.O) || (this.f201v.getVisibility() != 0 && this.f203x.getVisibility() != 0)) {
            i2 = 8;
        }
        this.f199t.setVisibility(i2);
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0021  */
    public final void w(boolean z2) {
        int i2;
        boolean z3 = this.Q;
        if (z3) {
            i2 = 0;
            if (!((z3 || this.V) && !this.O) || !hasFocus() || (!z2 && this.V)) {
                i2 = 8;
            }
        } else {
            i2 = 8;
        }
        this.f201v.setVisibility(i2);
    }

    public final void x(boolean z2) {
        this.O = z2;
        int i2 = 8;
        int i3 = z2 ? 0 : 8;
        boolean z3 = !TextUtils.isEmpty(this.f196q.getText());
        this.f200u.setVisibility(i3);
        w(z3);
        this.f197r.setVisibility(z2 ? 8 : 0);
        ImageView imageView = this.E;
        imageView.setVisibility((imageView.getDrawable() == null || this.N) ? 8 : 0);
        s();
        boolean z4 = !z3;
        if (this.V && !this.O && z4) {
            this.f201v.setVisibility(8);
            i2 = 0;
        }
        this.f203x.setVisibility(i2);
        v();
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.A = new Rect();
        this.B = new Rect();
        this.C = new int[2];
        this.D = new int[2];
        this.f194e0 = new e2(this, 0);
        this.f0 = new e2(this, 1);
        this.f195g0 = new WeakHashMap();
        a aVar = new a(this);
        b bVar = new b(this);
        h2 h2Var = new h2(this);
        i2 i2Var = new i2(this);
        p1 p1Var = new p1(this, 1);
        d2 d2Var = new d2(this, 0);
        m0.a aVar2 = new m0.a(context, context.obtainStyledAttributes(attributeSet, c.a.f500t, i2, 0));
        LayoutInflater.from(context).inflate(aVar2.p(9, R.layout.abc_search_view), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(R.id.search_src_text);
        this.f196q = searchAutoComplete;
        searchAutoComplete.setSearchView(this);
        this.f197r = findViewById(R.id.search_edit_frame);
        View viewFindViewById = findViewById(R.id.search_plate);
        this.f198s = viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.submit_area);
        this.f199t = viewFindViewById2;
        ImageView imageView = (ImageView) findViewById(R.id.search_button);
        this.f200u = imageView;
        ImageView imageView2 = (ImageView) findViewById(R.id.search_go_btn);
        this.f201v = imageView2;
        ImageView imageView3 = (ImageView) findViewById(R.id.search_close_btn);
        this.f202w = imageView3;
        ImageView imageView4 = (ImageView) findViewById(R.id.search_voice_btn);
        this.f203x = imageView4;
        ImageView imageView5 = (ImageView) findViewById(R.id.search_mag_icon);
        this.E = imageView5;
        viewFindViewById.setBackground(aVar2.k(10));
        viewFindViewById2.setBackground(aVar2.k(14));
        imageView.setImageDrawable(aVar2.k(13));
        imageView2.setImageDrawable(aVar2.k(7));
        imageView3.setImageDrawable(aVar2.k(4));
        imageView4.setImageDrawable(aVar2.k(16));
        imageView5.setImageDrawable(aVar2.k(13));
        this.F = aVar2.k(12);
        i.g0(imageView, getResources().getString(R.string.abc_searchview_description_search));
        this.G = aVar2.p(15, R.layout.abc_search_dropdown_item_icons_2line);
        this.H = aVar2.p(5, 0);
        imageView.setOnClickListener(aVar);
        imageView3.setOnClickListener(aVar);
        imageView2.setOnClickListener(aVar);
        imageView4.setOnClickListener(aVar);
        searchAutoComplete.setOnClickListener(aVar);
        searchAutoComplete.addTextChangedListener(d2Var);
        searchAutoComplete.setOnEditorActionListener(h2Var);
        searchAutoComplete.setOnItemClickListener(i2Var);
        searchAutoComplete.setOnItemSelectedListener(p1Var);
        searchAutoComplete.setOnKeyListener(bVar);
        searchAutoComplete.setOnFocusChangeListener(new f2(this, 0));
        setIconifiedByDefault(aVar2.g(8, true));
        int iJ = aVar2.j(1, -1);
        if (iJ != -1) {
            setMaxWidth(iJ);
        }
        this.K = aVar2.r(6);
        this.R = aVar2.r(11);
        int iN = aVar2.n(3, -1);
        if (iN != -1) {
            setImeOptions(iN);
        }
        int iN2 = aVar2.n(2, -1);
        if (iN2 != -1) {
            setInputType(iN2);
        }
        setFocusable(aVar2.g(0, true));
        aVar2.w();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.I = intent;
        intent.addFlags(268435456);
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.J = intent2;
        intent2.addFlags(268435456);
        View viewFindViewById3 = findViewById(searchAutoComplete.getDropDownAnchor());
        this.f204y = viewFindViewById3;
        if (viewFindViewById3 != null) {
            viewFindViewById3.addOnLayoutChangeListener(new g2(this));
        }
        x(this.N);
        u();
    }
}
