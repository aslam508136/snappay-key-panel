package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.snapay.app.R;
import i.c0;
import i.o;
import i.q;
import java.util.WeakHashMap;
import m0.a;
import x.u;

/* JADX INFO: loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements c0, AbsListView.SelectionBoundsAdjuster {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public q f95b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ImageView f96c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public RadioButton f97d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f98e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CheckBox f99f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TextView f100g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ImageView f101h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ImageView f102i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public LinearLayout f103j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Drawable f104k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f105l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Context f106m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f107n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Drawable f108o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f109p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public LayoutInflater f110q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f111r;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a aVarU = a.u(getContext(), attributeSet, c.a.f497q, R.attr.listMenuViewStyle);
        this.f104k = aVarU.k(5);
        this.f105l = aVarU.p(1, -1);
        this.f107n = aVarU.g(7, false);
        this.f106m = context;
        this.f108o = aVarU.k(8);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{android.R.attr.divider}, R.attr.dropDownListViewStyle, 0);
        this.f109p = typedArrayObtainStyledAttributes.hasValue(0);
        aVarU.w();
        typedArrayObtainStyledAttributes.recycle();
    }

    private LayoutInflater getInflater() {
        if (this.f110q == null) {
            this.f110q = LayoutInflater.from(getContext());
        }
        return this.f110q;
    }

    private void setSubMenuArrowVisible(boolean z2) {
        ImageView imageView = this.f101h;
        if (imageView != null) {
            imageView.setVisibility(z2 ? 0 : 8);
        }
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public final void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.f102i;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f102i.getLayoutParams();
        rect.top = this.f102i.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin + rect.top;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0036  */
    /* JADX WARN: Code duplicated, block: B:25:0x0055  */
    /* JADX WARN: Code duplicated, block: B:28:0x0059  */
    @Override // i.c0
    public final void c(q qVar) {
        boolean z2;
        int i2;
        int i3;
        String string;
        boolean z3;
        this.f95b = qVar;
        setVisibility(qVar.isVisible() ? 0 : 8);
        setTitle(qVar.f1091e);
        setCheckable(qVar.isCheckable());
        o oVar = qVar.f1100n;
        if (oVar.o()) {
            if ((oVar.n() ? qVar.f1096j : qVar.f1094h) != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
        } else {
            z2 = false;
        }
        oVar.n();
        if (z2) {
            q qVar2 = this.f95b;
            o oVar2 = qVar2.f1100n;
            if (oVar2.o()) {
                if ((oVar2.n() ? qVar2.f1096j : qVar2.f1094h) != 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
            } else {
                z3 = false;
            }
            i2 = z3 ? 0 : 8;
        }
        if (i2 == 0) {
            TextView textView = this.f100g;
            q qVar3 = this.f95b;
            char c2 = qVar3.f1100n.n() ? qVar3.f1096j : qVar3.f1094h;
            if (c2 == 0) {
                string = "";
            } else {
                o oVar3 = qVar3.f1100n;
                Resources resources = oVar3.f1060a.getResources();
                StringBuilder sb = new StringBuilder();
                if (ViewConfiguration.get(oVar3.f1060a).hasPermanentMenuKey()) {
                    sb.append(resources.getString(R.string.abc_prepend_shortcut_label));
                }
                int i4 = oVar3.n() ? qVar3.f1097k : qVar3.f1095i;
                q.c(sb, i4, 65536, resources.getString(R.string.abc_menu_meta_shortcut_label));
                q.c(sb, i4, 4096, resources.getString(R.string.abc_menu_ctrl_shortcut_label));
                q.c(sb, i4, 2, resources.getString(R.string.abc_menu_alt_shortcut_label));
                q.c(sb, i4, 1, resources.getString(R.string.abc_menu_shift_shortcut_label));
                q.c(sb, i4, 4, resources.getString(R.string.abc_menu_sym_shortcut_label));
                q.c(sb, i4, 8, resources.getString(R.string.abc_menu_function_shortcut_label));
                if (c2 == '\b') {
                    i3 = R.string.abc_menu_delete_shortcut_label;
                } else if (c2 != '\n') {
                    if (c2 != ' ') {
                        sb.append(c2);
                    } else {
                        i3 = R.string.abc_menu_space_shortcut_label;
                    }
                    string = sb.toString();
                } else {
                    i3 = R.string.abc_menu_enter_shortcut_label;
                }
                sb.append(resources.getString(i3));
                string = sb.toString();
            }
            textView.setText(string);
        }
        if (this.f100g.getVisibility() != i2) {
            this.f100g.setVisibility(i2);
        }
        setIcon(qVar.getIcon());
        setEnabled(qVar.isEnabled());
        setSubMenuArrowVisible(qVar.hasSubMenu());
        setContentDescription(qVar.f1103q);
    }

    @Override // i.c0
    public q getItemData() {
        return this.f95b;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        WeakHashMap weakHashMap = u.f2012a;
        setBackground(this.f104k);
        TextView textView = (TextView) findViewById(R.id.title);
        this.f98e = textView;
        int i2 = this.f105l;
        if (i2 != -1) {
            textView.setTextAppearance(this.f106m, i2);
        }
        this.f100g = (TextView) findViewById(R.id.shortcut);
        ImageView imageView = (ImageView) findViewById(R.id.submenuarrow);
        this.f101h = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.f108o);
        }
        this.f102i = (ImageView) findViewById(R.id.group_divider);
        this.f103j = (LinearLayout) findViewById(R.id.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i2, int i3) {
        if (this.f96c != null && this.f107n) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f96c.getLayoutParams();
            int i4 = layoutParams.height;
            if (i4 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i4;
            }
        }
        super.onMeasure(i2, i3);
    }

    public void setCheckable(boolean z2) {
        CompoundButton compoundButton;
        View view;
        if (!z2 && this.f97d == null && this.f99f == null) {
            return;
        }
        if ((this.f95b.f1110x & 4) != 0) {
            if (this.f97d == null) {
                RadioButton radioButton = (RadioButton) getInflater().inflate(R.layout.abc_list_menu_item_radio, (ViewGroup) this, false);
                this.f97d = radioButton;
                LinearLayout linearLayout = this.f103j;
                if (linearLayout != null) {
                    linearLayout.addView(radioButton, -1);
                } else {
                    addView(radioButton, -1);
                }
            }
            compoundButton = this.f97d;
            view = this.f99f;
        } else {
            if (this.f99f == null) {
                CheckBox checkBox = (CheckBox) getInflater().inflate(R.layout.abc_list_menu_item_checkbox, (ViewGroup) this, false);
                this.f99f = checkBox;
                LinearLayout linearLayout2 = this.f103j;
                if (linearLayout2 != null) {
                    linearLayout2.addView(checkBox, -1);
                } else {
                    addView(checkBox, -1);
                }
            }
            compoundButton = this.f99f;
            view = this.f97d;
        }
        if (z2) {
            compoundButton.setChecked(this.f95b.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (view == null || view.getVisibility() == 8) {
                return;
            }
            view.setVisibility(8);
            return;
        }
        CheckBox checkBox2 = this.f99f;
        if (checkBox2 != null) {
            checkBox2.setVisibility(8);
        }
        RadioButton radioButton2 = this.f97d;
        if (radioButton2 != null) {
            radioButton2.setVisibility(8);
        }
    }

    public void setChecked(boolean z2) {
        CompoundButton compoundButton;
        if ((this.f95b.f1110x & 4) != 0) {
            if (this.f97d == null) {
                RadioButton radioButton = (RadioButton) getInflater().inflate(R.layout.abc_list_menu_item_radio, (ViewGroup) this, false);
                this.f97d = radioButton;
                LinearLayout linearLayout = this.f103j;
                if (linearLayout != null) {
                    linearLayout.addView(radioButton, -1);
                } else {
                    addView(radioButton, -1);
                }
            }
            compoundButton = this.f97d;
        } else {
            if (this.f99f == null) {
                CheckBox checkBox = (CheckBox) getInflater().inflate(R.layout.abc_list_menu_item_checkbox, (ViewGroup) this, false);
                this.f99f = checkBox;
                LinearLayout linearLayout2 = this.f103j;
                if (linearLayout2 != null) {
                    linearLayout2.addView(checkBox, -1);
                } else {
                    addView(checkBox, -1);
                }
            }
            compoundButton = this.f99f;
        }
        compoundButton.setChecked(z2);
    }

    public void setForceShowIcon(boolean z2) {
        this.f111r = z2;
        this.f107n = z2;
    }

    public void setGroupDividerEnabled(boolean z2) {
        ImageView imageView = this.f102i;
        if (imageView != null) {
            imageView.setVisibility((this.f109p || !z2) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        this.f95b.f1100n.getClass();
        boolean z2 = this.f111r;
        if (z2 || this.f107n) {
            ImageView imageView = this.f96c;
            if (imageView == null && drawable == null && !this.f107n) {
                return;
            }
            if (imageView == null) {
                ImageView imageView2 = (ImageView) getInflater().inflate(R.layout.abc_list_menu_item_icon, (ViewGroup) this, false);
                this.f96c = imageView2;
                LinearLayout linearLayout = this.f103j;
                if (linearLayout != null) {
                    linearLayout.addView(imageView2, 0);
                } else {
                    addView(imageView2, 0);
                }
            }
            if (drawable == null && !this.f107n) {
                this.f96c.setVisibility(8);
                return;
            }
            ImageView imageView3 = this.f96c;
            if (!z2) {
                drawable = null;
            }
            imageView3.setImageDrawable(drawable);
            if (this.f96c.getVisibility() != 0) {
                this.f96c.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        int i2;
        TextView textView;
        if (charSequence != null) {
            this.f98e.setText(charSequence);
            if (this.f98e.getVisibility() == 0) {
                return;
            }
            textView = this.f98e;
            i2 = 0;
        } else {
            i2 = 8;
            if (this.f98e.getVisibility() == 8) {
                return;
            } else {
                textView = this.f98e;
            }
        }
        textView.setVisibility(i2);
    }
}
