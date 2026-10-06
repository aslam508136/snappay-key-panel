package j;

import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import com.snapay.app.R;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class q2 extends b0.b implements View.OnClickListener {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final /* synthetic */ int f1363y = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f1364i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f1365j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final LayoutInflater f1366k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final SearchView f1367l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final SearchableInfo f1368m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Context f1369n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final WeakHashMap f1370o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f1371p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1372q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ColorStateList f1373r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1374s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f1375t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f1376u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1377v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f1378w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f1379x;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q2(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap weakHashMap) {
        super(context);
        int suggestionRowLayout = searchView.getSuggestionRowLayout();
        this.f1365j = suggestionRowLayout;
        this.f1364i = suggestionRowLayout;
        this.f1366k = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f1372q = 1;
        this.f1374s = -1;
        this.f1375t = -1;
        this.f1376u = -1;
        this.f1377v = -1;
        this.f1378w = -1;
        this.f1379x = -1;
        this.f1367l = searchView;
        this.f1368m = searchableInfo;
        this.f1371p = searchView.getSuggestionCommitIconResId();
        this.f1369n = context;
        this.f1370o = weakHashMap;
    }

    public static String h(Cursor cursor, int i2) {
        if (i2 == -1) {
            return null;
        }
        try {
            return cursor.getString(i2);
        } catch (Exception e2) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e2);
            return null;
        }
    }

    @Override // b0.b
    public final void a(View view, Cursor cursor) throws FileNotFoundException {
        Drawable drawableF;
        String string;
        Drawable drawable;
        CharSequence charSequenceH;
        p2 p2Var = (p2) view.getTag();
        int i2 = this.f1379x;
        int i3 = i2 != -1 ? cursor.getInt(i2) : 0;
        TextView textView = p2Var.f1356a;
        if (textView != null) {
            String strH = h(cursor, this.f1374s);
            textView.setText(strH);
            textView.setVisibility(TextUtils.isEmpty(strH) ? 8 : 0);
        }
        Context context = this.f1369n;
        TextView textView2 = p2Var.f1357b;
        if (textView2 != null) {
            String strH2 = h(cursor, this.f1376u);
            if (strH2 != null) {
                if (this.f1373r == null) {
                    TypedValue typedValue = new TypedValue();
                    context.getTheme().resolveAttribute(R.attr.textColorSearchUrl, typedValue, true);
                    this.f1373r = context.getResources().getColorStateList(typedValue.resourceId);
                }
                SpannableString spannableString = new SpannableString(strH2);
                spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.f1373r, null), 0, strH2.length(), 33);
                charSequenceH = spannableString;
            } else {
                charSequenceH = h(cursor, this.f1375t);
            }
            if (TextUtils.isEmpty(charSequenceH)) {
                if (textView != null) {
                    textView.setSingleLine(false);
                    textView.setMaxLines(2);
                }
            } else if (textView != null) {
                textView.setSingleLine(true);
                textView.setMaxLines(1);
            }
            textView2.setText(charSequenceH);
            textView2.setVisibility(TextUtils.isEmpty(charSequenceH) ? 8 : 0);
        }
        ImageView imageView = p2Var.f1358c;
        if (imageView != null) {
            int i4 = this.f1377v;
            if (i4 == -1) {
                drawableF = null;
            } else {
                drawableF = f(cursor.getString(i4));
                if (drawableF == null) {
                    ComponentName searchActivity = this.f1368m.getSearchActivity();
                    String strFlattenToShortString = searchActivity.flattenToShortString();
                    WeakHashMap weakHashMap = this.f1370o;
                    if (weakHashMap.containsKey(strFlattenToShortString)) {
                        Drawable.ConstantState constantState = (Drawable.ConstantState) weakHashMap.get(strFlattenToShortString);
                        drawableF = constantState == null ? null : constantState.newDrawable(context.getResources());
                    } else {
                        PackageManager packageManager = context.getPackageManager();
                        try {
                            ActivityInfo activityInfo = packageManager.getActivityInfo(searchActivity, 128);
                            int iconResource = activityInfo.getIconResource();
                            if (iconResource != 0) {
                                drawable = packageManager.getDrawable(searchActivity.getPackageName(), iconResource, activityInfo.applicationInfo);
                                if (drawable == null) {
                                    string = "Invalid icon resource " + iconResource + " for " + searchActivity.flattenToShortString();
                                    Log.w("SuggestionsAdapter", string);
                                    drawable = null;
                                }
                            } else {
                                drawable = null;
                            }
                        } catch (PackageManager.NameNotFoundException e2) {
                            string = e2.toString();
                        }
                        weakHashMap.put(strFlattenToShortString, drawable == null ? null : drawable.getConstantState());
                        drawableF = drawable;
                    }
                    if (drawableF == null) {
                        drawableF = context.getPackageManager().getDefaultActivityIcon();
                    }
                }
            }
            imageView.setImageDrawable(drawableF);
            if (drawableF == null) {
                imageView.setVisibility(4);
            } else {
                imageView.setVisibility(0);
                drawableF.setVisible(false, false);
                drawableF.setVisible(true, false);
            }
        }
        ImageView imageView2 = p2Var.f1359d;
        if (imageView2 != null) {
            int i5 = this.f1378w;
            Drawable drawableF2 = i5 == -1 ? null : f(cursor.getString(i5));
            imageView2.setImageDrawable(drawableF2);
            if (drawableF2 == null) {
                imageView2.setVisibility(8);
            } else {
                imageView2.setVisibility(0);
                drawableF2.setVisible(false, false);
                drawableF2.setVisible(true, false);
            }
        }
        int i6 = this.f1372q;
        ImageView imageView3 = p2Var.f1360e;
        if (i6 != 2 && (i6 != 1 || (i3 & 1) == 0)) {
            imageView3.setVisibility(8);
            return;
        }
        imageView3.setVisibility(0);
        imageView3.setTag(textView.getText());
        imageView3.setOnClickListener(this);
    }

    @Override // b0.b
    public final void b(Cursor cursor) {
        try {
            super.b(cursor);
            if (cursor != null) {
                this.f1374s = cursor.getColumnIndex("suggest_text_1");
                this.f1375t = cursor.getColumnIndex("suggest_text_2");
                this.f1376u = cursor.getColumnIndex("suggest_text_2_url");
                this.f1377v = cursor.getColumnIndex("suggest_icon_1");
                this.f1378w = cursor.getColumnIndex("suggest_icon_2");
                this.f1379x = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e2) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e2);
        }
    }

    @Override // b0.b
    public final String c(Cursor cursor) {
        String strH;
        String strH2;
        if (cursor == null) {
            return null;
        }
        String strH3 = h(cursor, cursor.getColumnIndex("suggest_intent_query"));
        if (strH3 != null) {
            return strH3;
        }
        SearchableInfo searchableInfo = this.f1368m;
        if (searchableInfo.shouldRewriteQueryFromData() && (strH2 = h(cursor, cursor.getColumnIndex("suggest_intent_data"))) != null) {
            return strH2;
        }
        if (!searchableInfo.shouldRewriteQueryFromText() || (strH = h(cursor, cursor.getColumnIndex("suggest_text_1"))) == null) {
            return null;
        }
        return strH;
    }

    @Override // b0.b
    public final View d(ViewGroup viewGroup) {
        View viewInflate = this.f1366k.inflate(this.f1364i, viewGroup, false);
        viewInflate.setTag(new p2(viewInflate));
        ((ImageView) viewInflate.findViewById(R.id.edit_query)).setImageResource(this.f1371p);
        return viewInflate;
    }

    public final Drawable e(Uri uri) throws FileNotFoundException {
        int identifier;
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        try {
            Resources resourcesForApplication = this.f1369n.getPackageManager().getResourcesForApplication(authority);
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                throw new FileNotFoundException("No path: " + uri);
            }
            int size = pathSegments.size();
            if (size == 1) {
                try {
                    identifier = Integer.parseInt(pathSegments.get(0));
                } catch (NumberFormatException unused) {
                    throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                }
            } else {
                if (size != 2) {
                    throw new FileNotFoundException("More than two path segments: " + uri);
                }
                identifier = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
            }
            if (identifier != 0) {
                return resourcesForApplication.getDrawable(identifier);
            }
            throw new FileNotFoundException("No resource found for: " + uri);
        } catch (PackageManager.NameNotFoundException unused2) {
            throw new FileNotFoundException("No package found for authority: " + uri);
        }
    }

    public final Drawable f(String str) throws FileNotFoundException {
        WeakHashMap weakHashMap = this.f1370o;
        Context context = this.f1369n;
        Drawable drawableE = null;
        if (str != null && !str.isEmpty() && !"0".equals(str)) {
            try {
                int i2 = Integer.parseInt(str);
                String str2 = "android.resource://" + context.getPackageName() + "/" + i2;
                Drawable.ConstantState constantState = (Drawable.ConstantState) weakHashMap.get(str2);
                Drawable drawableNewDrawable = constantState == null ? null : constantState.newDrawable();
                if (drawableNewDrawable != null) {
                    return drawableNewDrawable;
                }
                Object obj = o.a.f1732a;
                Drawable drawable = context.getDrawable(i2);
                if (drawable != null) {
                    weakHashMap.put(str2, drawable.getConstantState());
                }
                return drawable;
            } catch (Resources.NotFoundException unused) {
                Log.w("SuggestionsAdapter", "Icon resource not found: ".concat(str));
                return null;
            } catch (NumberFormatException unused2) {
                Drawable.ConstantState constantState2 = (Drawable.ConstantState) weakHashMap.get(str);
                Drawable drawableNewDrawable2 = constantState2 == null ? null : constantState2.newDrawable();
                if (drawableNewDrawable2 != null) {
                    return drawableNewDrawable2;
                }
                Uri uri = Uri.parse(str);
                try {
                    if ("android.resource".equals(uri.getScheme())) {
                        try {
                            drawableE = e(uri);
                        } catch (Resources.NotFoundException unused3) {
                            throw new FileNotFoundException("Resource does not exist: " + uri);
                        }
                    } else {
                        InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
                        if (inputStreamOpenInputStream == null) {
                            throw new FileNotFoundException("Failed to open " + uri);
                        }
                        try {
                            Drawable drawableCreateFromStream = Drawable.createFromStream(inputStreamOpenInputStream, null);
                            try {
                                inputStreamOpenInputStream.close();
                            } catch (IOException e2) {
                                Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e2);
                            }
                            drawableE = drawableCreateFromStream;
                        } catch (Throwable th) {
                            try {
                                inputStreamOpenInputStream.close();
                            } catch (IOException e3) {
                                Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e3);
                            }
                            throw th;
                        }
                    }
                } catch (FileNotFoundException e4) {
                    Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e4.getMessage());
                }
                if (drawableE != null) {
                    weakHashMap.put(str, drawableE.getConstantState());
                }
            }
        }
        return drawableE;
    }

    public final Cursor g(SearchableInfo searchableInfo, String str) {
        String suggestAuthority;
        String[] strArr = null;
        if (searchableInfo == null || (suggestAuthority = searchableInfo.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder builderFragment = new Uri.Builder().scheme("content").authority(suggestAuthority).query("").fragment("");
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            builderFragment.appendEncodedPath(suggestPath);
        }
        builderFragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            builderFragment.appendPath(str);
        }
        builderFragment.appendQueryParameter("limit", String.valueOf(50));
        return this.f1369n.getContentResolver().query(builderFragment.build(), null, suggestSelection, strArr, null);
    }

    @Override // b0.b, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public final View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i2, view, viewGroup);
        } catch (RuntimeException e2) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e2);
            View viewInflate = this.f1366k.inflate(this.f1365j, viewGroup, false);
            if (viewInflate != null) {
                ((p2) viewInflate.getTag()).f1356a.setText(e2.toString());
            }
            return viewInflate;
        }
    }

    @Override // b0.b, android.widget.Adapter
    public final View getView(int i2, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i2, view, viewGroup);
        } catch (RuntimeException e2) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e2);
            View viewD = d(viewGroup);
            ((p2) viewD.getTag()).f1356a.setText(e2.toString());
            return viewD;
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        Cursor cursor = this.f475d;
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras != null) {
            extras.getBoolean("in_progress");
        }
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        Cursor cursor = this.f475d;
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras != null) {
            extras.getBoolean("in_progress");
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.f1367l.q((CharSequence) tag);
        }
    }
}
