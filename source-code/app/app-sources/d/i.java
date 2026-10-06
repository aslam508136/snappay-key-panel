package d;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertController$RecycleListView;
import androidx.core.widget.NestedScrollView;
import com.snapay.app.R;

/* JADX INFO: loaded from: classes.dex */
public final class i {
    public final boolean A;
    public final g B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f652a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k f653b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Window f654c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f655d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f656e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f657f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public AlertController$RecycleListView f658g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Button f659h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public CharSequence f660i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Message f661j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Drawable f662k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Button f663l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Button f664m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public NestedScrollView f665n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Drawable f667p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ImageView f668q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public TextView f669r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public TextView f670s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public View f671t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ListAdapter f672u;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f674w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f675x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final int f676y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final int f677z;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f666o = 0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f673v = -1;
    public final b C = new b(this);

    public i(Context context, k kVar, Window window) {
        this.f652a = context;
        this.f653b = kVar;
        this.f654c = window;
        this.B = new g(kVar);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, c.a.f485e, R.attr.alertDialogStyle, 0);
        this.f674w = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.getResourceId(2, 0);
        this.f675x = typedArrayObtainStyledAttributes.getResourceId(4, 0);
        typedArrayObtainStyledAttributes.getResourceId(5, 0);
        this.f676y = typedArrayObtainStyledAttributes.getResourceId(7, 0);
        this.f677z = typedArrayObtainStyledAttributes.getResourceId(3, 0);
        this.A = typedArrayObtainStyledAttributes.getBoolean(6, true);
        this.f655d = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        typedArrayObtainStyledAttributes.recycle();
        kVar.h().g(1);
    }

    public static void a(View view, View view2, View view3) {
        if (view2 != null) {
            view2.setVisibility(view.canScrollVertically(-1) ? 0 : 4);
        }
        if (view3 != null) {
            view3.setVisibility(view.canScrollVertically(1) ? 0 : 4);
        }
    }

    public static ViewGroup b(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }
}
