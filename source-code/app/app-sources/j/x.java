package j;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import com.snapay.app.R;

/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f1487a = {R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f1488b = {R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int[] f1489c = {R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl, R.drawable.abc_text_select_handle_middle_mtrl, R.drawable.abc_text_select_handle_right_mtrl};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int[] f1490d = {R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int[] f1491e = {R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int[] f1492f = {R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material, R.drawable.abc_btn_check_material_anim, R.drawable.abc_btn_radio_material_anim};

    public static boolean a(int[] iArr, int i2) {
        for (int i3 : iArr) {
            if (i3 == i2) {
                return true;
            }
        }
        return false;
    }

    public static ColorStateList b(Context context, int i2) {
        int iC = r2.c(context, R.attr.colorControlHighlight);
        return new ColorStateList(new int[][]{r2.f1394b, r2.f1396d, r2.f1395c, r2.f1398f}, new int[]{r2.b(context, R.attr.colorButtonNormal), r.a.a(iC, i2), r.a.a(iC, i2), i2});
    }

    public static void d(Drawable drawable, int i2, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilterH;
        if (g1.a(drawable)) {
            drawable = drawable.mutate();
        }
        if (mode == null) {
            mode = y.f1495b;
        }
        PorterDuff.Mode mode2 = y.f1495b;
        synchronized (y.class) {
            porterDuffColorFilterH = a2.h(i2, mode);
        }
        drawable.setColorFilter(porterDuffColorFilterH);
    }

    public final ColorStateList c(Context context, int i2) {
        if (i2 == R.drawable.abc_edit_text_material) {
            return e.b.b(context, R.color.abc_tint_edittext);
        }
        if (i2 == R.drawable.abc_switch_track_mtrl_alpha) {
            return e.b.b(context, R.color.abc_tint_switch_track);
        }
        if (i2 != R.drawable.abc_switch_thumb_material) {
            if (i2 == R.drawable.abc_btn_default_mtrl_shape) {
                return b(context, r2.c(context, R.attr.colorButtonNormal));
            }
            if (i2 == R.drawable.abc_btn_borderless_material) {
                return b(context, 0);
            }
            if (i2 == R.drawable.abc_btn_colored_material) {
                return b(context, r2.c(context, R.attr.colorAccent));
            }
            if (i2 == R.drawable.abc_spinner_mtrl_am_alpha || i2 == R.drawable.abc_spinner_textfield_background_material) {
                return e.b.b(context, R.color.abc_tint_spinner);
            }
            if (a(this.f1488b, i2)) {
                return r2.d(context, R.attr.colorControlNormal);
            }
            if (a(this.f1491e, i2)) {
                return e.b.b(context, R.color.abc_tint_default);
            }
            if (a(this.f1492f, i2)) {
                return e.b.b(context, R.color.abc_tint_btn_checkable);
            }
            if (i2 == R.drawable.abc_seekbar_thumb_material) {
                return e.b.b(context, R.color.abc_tint_seek_thumb);
            }
            return null;
        }
        int[][] iArr = new int[3][];
        int[] iArr2 = new int[3];
        ColorStateList colorStateListD = r2.d(context, R.attr.colorSwitchThumbNormal);
        if (colorStateListD == null || !colorStateListD.isStateful()) {
            iArr[0] = r2.f1394b;
            iArr2[0] = r2.b(context, R.attr.colorSwitchThumbNormal);
            iArr[1] = r2.f1397e;
            iArr2[1] = r2.c(context, R.attr.colorControlActivated);
            iArr[2] = r2.f1398f;
            iArr2[2] = r2.c(context, R.attr.colorSwitchThumbNormal);
        } else {
            int[] iArr3 = r2.f1394b;
            iArr[0] = iArr3;
            iArr2[0] = colorStateListD.getColorForState(iArr3, 0);
            iArr[1] = r2.f1397e;
            iArr2[1] = r2.c(context, R.attr.colorControlActivated);
            iArr[2] = r2.f1398f;
            iArr2[2] = colorStateListD.getDefaultColor();
        }
        return new ColorStateList(iArr, iArr2);
    }
}
