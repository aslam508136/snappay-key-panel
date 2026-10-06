package d;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertController$RecycleListView;
import androidx.core.widget.NestedScrollView;
import com.snapay.app.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class k extends Dialog implements DialogInterface, o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a0 f706b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b0 f707c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final i f708d;

    /* JADX WARN: Illegal instructions before constructor call */
    public k(Context context, int i2) {
        int iM = m(context, i2);
        super(context, i(context, iM));
        this.f707c = new b0(this);
        p pVarH = h();
        ((a0) pVarH).O = i(context, iM);
        pVarH.c();
        this.f708d = new i(getContext(), this, getWindow());
    }

    public static int i(Context context, int i2) {
        if (i2 != 0) {
            return i2;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.dialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public static int m(Context context, int i2) {
        if (((i2 >>> 24) & 255) >= 1) {
            return i2;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    @Override // android.app.Dialog
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a0 a0Var = (a0) h();
        a0Var.r();
        ((ViewGroup) a0Var.f599v.findViewById(android.R.id.content)).addView(view, layoutParams);
        a0Var.f584g.f722b.onContentChanged();
    }

    @Override // d.o
    public final /* bridge */ /* synthetic */ void b() {
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final void dismiss() {
        super.dismiss();
        h().e();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return androidx.lifecycle.i.v(this.f707c, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // android.app.Dialog
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public final View findViewById(int i2) {
        a0 a0Var = (a0) h();
        a0Var.r();
        return a0Var.f583f.findViewById(i2);
    }

    @Override // d.o
    public final /* bridge */ /* synthetic */ void f() {
    }

    @Override // d.o
    public final /* bridge */ /* synthetic */ void g() {
    }

    public final p h() {
        if (this.f706b == null) {
            m.c cVar = p.f712b;
            this.f706b = new a0(getContext(), getWindow(), this, this);
        }
        return this.f706b;
    }

    @Override // android.app.Dialog
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public final void invalidateOptionsMenu() {
        a0 a0Var = (a0) h();
        a0Var.w();
        a0Var.U |= 1;
        if (a0Var.T) {
            return;
        }
        View decorView = a0Var.f583f.getDecorView();
        WeakHashMap weakHashMap = x.u.f2012a;
        decorView.postOnAnimation(a0Var.V);
        a0Var.T = true;
    }

    public final void k(Bundle bundle) {
        h().a();
        super.onCreate(bundle);
        h().c();
    }

    @Override // android.app.Dialog
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public final void onStop() {
        super.onStop();
        a0 a0Var = (a0) h();
        a0Var.L = false;
        a0Var.w();
        i0 i0Var = a0Var.f586i;
        if (i0Var != null) {
            i0Var.f699t = false;
            h.m mVar = i0Var.f698s;
            if (mVar != null) {
                mVar.a();
            }
        }
    }

    @Override // android.app.Dialog
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public final void setContentView(int i2) {
        h().h(i2);
    }

    @Override // android.app.Dialog
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public final void setContentView(View view) {
        a0 a0Var = (a0) h();
        a0Var.r();
        ViewGroup viewGroup = (ViewGroup) a0Var.f599v.findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        a0Var.f584g.f722b.onContentChanged();
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0283  */
    /* JADX WARN: Code duplicated, block: B:104:0x0289  */
    /* JADX WARN: Code duplicated, block: B:105:0x028e  */
    /* JADX WARN: Code duplicated, block: B:108:0x0296  */
    /* JADX WARN: Code duplicated, block: B:109:0x029b  */
    /* JADX WARN: Code duplicated, block: B:112:0x02a2  */
    /* JADX WARN: Code duplicated, block: B:115:0x02a7  */
    /* JADX WARN: Code duplicated, block: B:117:0x02ab A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:118:0x02ad  */
    /* JADX WARN: Code duplicated, block: B:119:0x02af  */
    /* JADX WARN: Code duplicated, block: B:122:0x02c5  */
    /* JADX WARN: Code duplicated, block: B:124:0x02c9  */
    /* JADX WARN: Code duplicated, block: B:126:0x02ce  */
    /* JADX WARN: Code duplicated, block: B:129:0x02d4  */
    /* JADX WARN: Code duplicated, block: B:139:0x02ec  */
    /* JADX WARN: Code duplicated, block: B:141:0x02f0  */
    /* JADX WARN: Code duplicated, block: B:142:0x0302  */
    /* JADX WARN: Code duplicated, block: B:144:0x0306  */
    /* JADX WARN: Code duplicated, block: B:146:0x0319 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:147:0x031b  */
    /* JADX WARN: Code duplicated, block: B:149:0x0320 A[PHI: r0
  0x0320: PHI (r0v6 android.view.View) = (r0v4 android.view.View), (r0v3 android.view.View) binds: [B:148:0x031e, B:127:0x02d1] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:66:0x0227  */
    /* JADX WARN: Code duplicated, block: B:67:0x0229  */
    /* JADX WARN: Code duplicated, block: B:72:0x0234  */
    /* JADX WARN: Code duplicated, block: B:75:0x023b  */
    /* JADX WARN: Code duplicated, block: B:76:0x023d  */
    /* JADX WARN: Code duplicated, block: B:82:0x024e  */
    /* JADX WARN: Code duplicated, block: B:84:0x0252  */
    /* JADX WARN: Code duplicated, block: B:91:0x0260  */
    /* JADX WARN: Code duplicated, block: B:94:0x026a  */
    /* JADX WARN: Code duplicated, block: B:96:0x0273 A[PHI: r2
  0x0273: PHI (r2v5 android.view.View) = (r2v4 android.view.View), (r2v17 android.view.View) binds: [B:95:0x0271, B:92:0x0267] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:99:0x027c  */
    @Override // android.app.Dialog
    public final void onCreate(Bundle bundle) {
        int i2;
        View viewFindViewById;
        boolean z2;
        int i3;
        boolean z3;
        View viewFindViewById2;
        AlertController$RecycleListView alertController$RecycleListView;
        AlertController$RecycleListView alertController$RecycleListView2;
        ListAdapter listAdapter;
        ViewGroup viewGroup;
        int i4;
        int i5;
        View viewFindViewById3;
        View viewFindViewById4;
        int i6;
        AlertController$RecycleListView alertController$RecycleListView3;
        View view;
        c cVar;
        int paddingTop;
        int paddingBottom;
        NestedScrollView nestedScrollView;
        View viewFindViewById5;
        Button button;
        k(bundle);
        i iVar = this.f708d;
        iVar.f653b.setContentView(iVar.f674w);
        Window window = iVar.f654c;
        View viewFindViewById6 = window.findViewById(R.id.parentPanel);
        View viewFindViewById7 = viewFindViewById6.findViewById(R.id.topPanel);
        View viewFindViewById8 = viewFindViewById6.findViewById(R.id.contentPanel);
        View viewFindViewById9 = viewFindViewById6.findViewById(R.id.buttonPanel);
        ViewGroup viewGroup2 = (ViewGroup) viewFindViewById6.findViewById(R.id.customPanel);
        window.setFlags(131072, 131072);
        viewGroup2.setVisibility(8);
        View viewFindViewById10 = viewGroup2.findViewById(R.id.topPanel);
        View viewFindViewById11 = viewGroup2.findViewById(R.id.contentPanel);
        View viewFindViewById12 = viewGroup2.findViewById(R.id.buttonPanel);
        ViewGroup viewGroupB = i.b(viewFindViewById10, viewFindViewById7);
        ViewGroup viewGroupB2 = i.b(viewFindViewById11, viewFindViewById8);
        ViewGroup viewGroupB3 = i.b(viewFindViewById12, viewFindViewById9);
        NestedScrollView nestedScrollView2 = (NestedScrollView) window.findViewById(R.id.scrollView);
        iVar.f665n = nestedScrollView2;
        int i7 = 0;
        nestedScrollView2.setFocusable(false);
        iVar.f665n.setNestedScrollingEnabled(false);
        TextView textView = (TextView) viewGroupB2.findViewById(android.R.id.message);
        iVar.f670s = textView;
        if (textView != null) {
            CharSequence charSequence = iVar.f657f;
            if (charSequence != null) {
                textView.setText(charSequence);
            } else {
                textView.setVisibility(8);
                iVar.f665n.removeView(iVar.f670s);
                if (iVar.f658g != null) {
                    ViewGroup viewGroup3 = (ViewGroup) iVar.f665n.getParent();
                    int iIndexOfChild = viewGroup3.indexOfChild(iVar.f665n);
                    viewGroup3.removeViewAt(iIndexOfChild);
                    viewGroup3.addView(iVar.f658g, iIndexOfChild, new ViewGroup.LayoutParams(-1, -1));
                } else {
                    viewGroupB2.setVisibility(8);
                }
            }
        }
        Button button2 = (Button) viewGroupB3.findViewById(android.R.id.button1);
        iVar.f659h = button2;
        b bVar = iVar.C;
        button2.setOnClickListener(bVar);
        boolean zIsEmpty = TextUtils.isEmpty(iVar.f660i);
        int i8 = 1;
        int i9 = iVar.f655d;
        if (zIsEmpty && iVar.f662k == null) {
            iVar.f659h.setVisibility(8);
            i2 = 0;
        } else {
            iVar.f659h.setText(iVar.f660i);
            Drawable drawable = iVar.f662k;
            if (drawable != null) {
                drawable.setBounds(0, 0, i9, i9);
                iVar.f659h.setCompoundDrawables(iVar.f662k, null, null, null);
            }
            iVar.f659h.setVisibility(0);
            i2 = 1;
        }
        Button button3 = (Button) viewGroupB3.findViewById(android.R.id.button2);
        iVar.f663l = button3;
        button3.setOnClickListener(bVar);
        iVar.getClass();
        if (TextUtils.isEmpty(null)) {
            iVar.getClass();
            iVar.f663l.setVisibility(8);
        } else {
            Button button4 = iVar.f663l;
            iVar.getClass();
            button4.setText((CharSequence) null);
            iVar.getClass();
            iVar.f663l.setVisibility(0);
            i2 |= 2;
        }
        Button button5 = (Button) viewGroupB3.findViewById(android.R.id.button3);
        iVar.f664m = button5;
        button5.setOnClickListener(bVar);
        iVar.getClass();
        if (TextUtils.isEmpty(null)) {
            iVar.getClass();
            iVar.f664m.setVisibility(8);
        } else {
            Button button6 = iVar.f664m;
            iVar.getClass();
            button6.setText((CharSequence) null);
            iVar.getClass();
            iVar.f664m.setVisibility(0);
            i2 |= 4;
        }
        TypedValue typedValue = new TypedValue();
        iVar.f652a.getTheme().resolveAttribute(R.attr.alertDialogCenterButtons, typedValue, true);
        if (typedValue.data != 0) {
            if (i2 == 1) {
                button = iVar.f659h;
            } else if (i2 == 2) {
                button = iVar.f663l;
            } else if (i2 == 4) {
                button = iVar.f664m;
            }
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
            layoutParams.gravity = 1;
            layoutParams.weight = 0.5f;
            button.setLayoutParams(layoutParams);
        }
        if (!(i2 != 0)) {
            viewGroupB3.setVisibility(8);
        }
        if (iVar.f671t == null) {
            iVar.f668q = (ImageView) window.findViewById(android.R.id.icon);
            if ((!TextUtils.isEmpty(iVar.f656e)) && iVar.A) {
                TextView textView2 = (TextView) window.findViewById(R.id.alertTitle);
                iVar.f669r = textView2;
                textView2.setText(iVar.f656e);
                int i10 = iVar.f666o;
                if (i10 != 0) {
                    iVar.f668q.setImageResource(i10);
                } else {
                    Drawable drawable2 = iVar.f667p;
                    if (drawable2 != null) {
                        iVar.f668q.setImageDrawable(drawable2);
                    } else {
                        iVar.f669r.setPadding(iVar.f668q.getPaddingLeft(), iVar.f668q.getPaddingTop(), iVar.f668q.getPaddingRight(), iVar.f668q.getPaddingBottom());
                        iVar.f668q.setVisibility(8);
                    }
                }
            } else {
                window.findViewById(R.id.title_template).setVisibility(8);
                iVar.f668q.setVisibility(8);
                viewFindViewById = viewGroupB;
            }
            if (viewGroup2.getVisibility() != 8) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (viewGroupB != null || viewGroupB.getVisibility() == 8) {
                i3 = 0;
            } else {
                i3 = 1;
            }
            if (viewGroupB3.getVisibility() != 8) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (!z3 && (viewFindViewById5 = viewGroupB2.findViewById(R.id.textSpacerNoButtons)) != null) {
                viewFindViewById5.setVisibility(0);
            }
            if (i3 != 0) {
                nestedScrollView = iVar.f665n;
                if (nestedScrollView != null) {
                    nestedScrollView.setClipToPadding(true);
                }
                if (iVar.f657f == null || iVar.f658g != null) {
                    viewFindViewById2 = viewGroupB.findViewById(R.id.titleDividerNoCustom);
                } else {
                    viewFindViewById2 = null;
                }
                if (viewFindViewById2 != null) {
                    viewFindViewById2.setVisibility(0);
                }
            } else {
                viewFindViewById2 = viewGroupB2.findViewById(R.id.textSpacerNoTitle);
                if (viewFindViewById2 != null) {
                    viewFindViewById2.setVisibility(0);
                }
            }
            alertController$RecycleListView = iVar.f658g;
            if (alertController$RecycleListView instanceof AlertController$RecycleListView) {
                alertController$RecycleListView.getClass();
                if (z3 || i3 == 0) {
                    int paddingLeft = alertController$RecycleListView.getPaddingLeft();
                    if (i3 != 0) {
                        paddingTop = alertController$RecycleListView.getPaddingTop();
                    } else {
                        paddingTop = alertController$RecycleListView.f80b;
                    }
                    int paddingRight = alertController$RecycleListView.getPaddingRight();
                    if (z3) {
                        paddingBottom = alertController$RecycleListView.getPaddingBottom();
                    } else {
                        paddingBottom = alertController$RecycleListView.f81c;
                    }
                    alertController$RecycleListView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
                }
            }
            if (!z2) {
                viewGroup = iVar.f658g;
                if (viewGroup == null) {
                    viewGroup = iVar.f665n;
                }
                if (viewGroup != null) {
                    if (z3) {
                        i4 = 2;
                    } else {
                        i4 = 0;
                    }
                    i5 = i4 | i3;
                    viewFindViewById3 = window.findViewById(R.id.scrollIndicatorUp);
                    viewFindViewById4 = window.findViewById(R.id.scrollIndicatorDown);
                    i6 = Build.VERSION.SDK_INT;
                    if (i6 >= 23) {
                        WeakHashMap weakHashMap = x.u.f2012a;
                        if (i6 >= 23) {
                            viewGroup.setScrollIndicators(i5, 3);
                        }
                        if (viewFindViewById3 != null) {
                            viewGroupB2.removeView(viewFindViewById3);
                        }
                        if (viewFindViewById4 != null) {
                            viewGroupB2.removeView(viewFindViewById4);
                        }
                    } else {
                        if (viewFindViewById3 != null && (i5 & 1) == 0) {
                            viewGroupB2.removeView(viewFindViewById3);
                            viewFindViewById3 = null;
                        }
                        if (viewFindViewById4 != null && (i5 & 2) == 0) {
                            viewGroupB2.removeView(viewFindViewById4);
                            viewFindViewById4 = null;
                        }
                        if (viewFindViewById3 == null || viewFindViewById4 != null) {
                            if (iVar.f657f != null) {
                                iVar.f665n.setOnScrollChangeListener(new m0.a(iVar, viewFindViewById3, viewFindViewById4));
                                view = iVar.f665n;
                                cVar = new c(iVar, viewFindViewById3, viewFindViewById4, i7);
                            } else {
                                alertController$RecycleListView3 = iVar.f658g;
                                if (alertController$RecycleListView3 != null) {
                                    alertController$RecycleListView3.setOnScrollListener(new d(viewFindViewById3, viewFindViewById4));
                                    view = iVar.f658g;
                                    cVar = new c(iVar, viewFindViewById3, viewFindViewById4, i8);
                                } else {
                                    if (viewFindViewById3 != null) {
                                        viewGroupB2.removeView(viewFindViewById3);
                                    }
                                    if (viewFindViewById4 != null) {
                                        viewGroupB2.removeView(viewFindViewById4);
                                    }
                                }
                            }
                            view.post(cVar);
                        }
                    }
                }
            }
            alertController$RecycleListView2 = iVar.f658g;
            if (alertController$RecycleListView2 != null || (listAdapter = iVar.f672u) == null) {
            }
            alertController$RecycleListView2.setAdapter(listAdapter);
            int i11 = iVar.f673v;
            if (i11 > -1) {
                alertController$RecycleListView2.setItemChecked(i11, true);
                alertController$RecycleListView2.setSelection(i11);
                return;
            }
            return;
        }
        viewGroupB.addView(iVar.f671t, 0, new ViewGroup.LayoutParams(-1, -2));
        viewFindViewById = window.findViewById(R.id.title_template);
        viewFindViewById.setVisibility(8);
        if (viewGroup2.getVisibility() != 8) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (viewGroupB != null) {
            i3 = 0;
        } else {
            i3 = 0;
        }
        if (viewGroupB3.getVisibility() != 8) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (!z3) {
            viewFindViewById5.setVisibility(0);
        }
        if (i3 != 0) {
            nestedScrollView = iVar.f665n;
            if (nestedScrollView != null) {
                nestedScrollView.setClipToPadding(true);
            }
            if (iVar.f657f == null) {
                viewFindViewById2 = viewGroupB.findViewById(R.id.titleDividerNoCustom);
            } else {
                viewFindViewById2 = viewGroupB.findViewById(R.id.titleDividerNoCustom);
            }
            if (viewFindViewById2 != null) {
                viewFindViewById2.setVisibility(0);
            }
        } else {
            viewFindViewById2 = viewGroupB2.findViewById(R.id.textSpacerNoTitle);
            if (viewFindViewById2 != null) {
                viewFindViewById2.setVisibility(0);
            }
        }
        alertController$RecycleListView = iVar.f658g;
        if (alertController$RecycleListView instanceof AlertController$RecycleListView) {
            alertController$RecycleListView.getClass();
            if (z3) {
                int paddingLeft2 = alertController$RecycleListView.getPaddingLeft();
                if (i3 != 0) {
                    paddingTop = alertController$RecycleListView.getPaddingTop();
                } else {
                    paddingTop = alertController$RecycleListView.f80b;
                }
                int paddingRight2 = alertController$RecycleListView.getPaddingRight();
                if (z3) {
                    paddingBottom = alertController$RecycleListView.getPaddingBottom();
                } else {
                    paddingBottom = alertController$RecycleListView.f81c;
                }
                alertController$RecycleListView.setPadding(paddingLeft2, paddingTop, paddingRight2, paddingBottom);
            } else {
                int paddingLeft3 = alertController$RecycleListView.getPaddingLeft();
                if (i3 != 0) {
                    paddingTop = alertController$RecycleListView.getPaddingTop();
                } else {
                    paddingTop = alertController$RecycleListView.f80b;
                }
                int paddingRight3 = alertController$RecycleListView.getPaddingRight();
                if (z3) {
                    paddingBottom = alertController$RecycleListView.getPaddingBottom();
                } else {
                    paddingBottom = alertController$RecycleListView.f81c;
                }
                alertController$RecycleListView.setPadding(paddingLeft3, paddingTop, paddingRight3, paddingBottom);
            }
        }
        if (!z2) {
            viewGroup = iVar.f658g;
            if (viewGroup == null) {
                viewGroup = iVar.f665n;
            }
            if (viewGroup != null) {
                if (z3) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i4 | i3;
                viewFindViewById3 = window.findViewById(R.id.scrollIndicatorUp);
                viewFindViewById4 = window.findViewById(R.id.scrollIndicatorDown);
                i6 = Build.VERSION.SDK_INT;
                if (i6 >= 23) {
                    WeakHashMap weakHashMap2 = x.u.f2012a;
                    if (i6 >= 23) {
                        viewGroup.setScrollIndicators(i5, 3);
                    }
                    if (viewFindViewById3 != null) {
                        viewGroupB2.removeView(viewFindViewById3);
                    }
                    if (viewFindViewById4 != null) {
                        viewGroupB2.removeView(viewFindViewById4);
                    }
                } else {
                    if (viewFindViewById3 != null) {
                        viewGroupB2.removeView(viewFindViewById3);
                        viewFindViewById3 = null;
                    }
                    if (viewFindViewById4 != null) {
                        viewGroupB2.removeView(viewFindViewById4);
                        viewFindViewById4 = null;
                    }
                    if (viewFindViewById3 == null) {
                        if (iVar.f657f != null) {
                            iVar.f665n.setOnScrollChangeListener(new m0.a(iVar, viewFindViewById3, viewFindViewById4));
                            view = iVar.f665n;
                            cVar = new c(iVar, viewFindViewById3, viewFindViewById4, i7);
                        } else {
                            alertController$RecycleListView3 = iVar.f658g;
                            if (alertController$RecycleListView3 != null) {
                                alertController$RecycleListView3.setOnScrollListener(new d(viewFindViewById3, viewFindViewById4));
                                view = iVar.f658g;
                                cVar = new c(iVar, viewFindViewById3, viewFindViewById4, i8);
                            } else {
                                if (viewFindViewById3 != null) {
                                    viewGroupB2.removeView(viewFindViewById3);
                                }
                                if (viewFindViewById4 != null) {
                                    viewGroupB2.removeView(viewFindViewById4);
                                }
                            }
                        }
                        view.post(cVar);
                    } else {
                        if (iVar.f657f != null) {
                            iVar.f665n.setOnScrollChangeListener(new m0.a(iVar, viewFindViewById3, viewFindViewById4));
                            view = iVar.f665n;
                            cVar = new c(iVar, viewFindViewById3, viewFindViewById4, i7);
                        } else {
                            alertController$RecycleListView3 = iVar.f658g;
                            if (alertController$RecycleListView3 != null) {
                                alertController$RecycleListView3.setOnScrollListener(new d(viewFindViewById3, viewFindViewById4));
                                view = iVar.f658g;
                                cVar = new c(iVar, viewFindViewById3, viewFindViewById4, i8);
                            } else {
                                if (viewFindViewById3 != null) {
                                    viewGroupB2.removeView(viewFindViewById3);
                                }
                                if (viewFindViewById4 != null) {
                                    viewGroupB2.removeView(viewFindViewById4);
                                }
                            }
                        }
                        view.post(cVar);
                    }
                }
            }
        }
        alertController$RecycleListView2 = iVar.f658g;
        if (alertController$RecycleListView2 != null) {
        }
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i2, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f708d.f665n;
        if (nestedScrollView != null && nestedScrollView.m(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i2, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f708d.f665n;
        if (nestedScrollView != null && nestedScrollView.m(keyEvent)) {
            return true;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    @Override // android.app.Dialog
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a0 a0Var = (a0) h();
        a0Var.r();
        ViewGroup viewGroup = (ViewGroup) a0Var.f599v.findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        a0Var.f584g.f722b.onContentChanged();
    }

    @Override // android.app.Dialog
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final void setTitle(int i2) {
        super.setTitle(i2);
        h().i(getContext().getString(i2));
    }

    public final void r(CharSequence charSequence) {
        super.setTitle(charSequence);
        h().i(charSequence);
    }

    final boolean s(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Dialog
    public final void setTitle(CharSequence charSequence) {
        r(charSequence);
        i iVar = this.f708d;
        iVar.f656e = charSequence;
        TextView textView = iVar.f669r;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }
}
