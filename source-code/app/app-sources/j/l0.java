package j;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ListAdapter;
import androidx.appcompat.app.AlertController$RecycleListView;

/* JADX INFO: loaded from: classes.dex */
public final class l0 implements q0, DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d.k f1281b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ListAdapter f1282c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CharSequence f1283d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ r0 f1284e;

    public l0(r0 r0Var) {
        this.f1284e = r0Var;
    }

    @Override // j.q0
    public final void a(int i2) {
        Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
    }

    @Override // j.q0
    public final boolean b() {
        d.k kVar = this.f1281b;
        if (kVar != null) {
            return kVar.isShowing();
        }
        return false;
    }

    @Override // j.q0
    public final int c() {
        return 0;
    }

    @Override // j.q0
    public final void d(int i2, int i3) {
        if (this.f1282c == null) {
            return;
        }
        r0 r0Var = this.f1284e;
        d.j jVar = new d.j(r0Var.getPopupContext());
        CharSequence charSequence = this.f1283d;
        Object obj = jVar.f705b;
        if (charSequence != null) {
            ((d.f) obj).f631d = charSequence;
        }
        ListAdapter listAdapter = this.f1282c;
        int selectedItemPosition = r0Var.getSelectedItemPosition();
        d.f fVar = (d.f) obj;
        fVar.f638k = listAdapter;
        fVar.f639l = this;
        fVar.f641n = selectedItemPosition;
        fVar.f640m = true;
        d.k kVarA = jVar.a();
        this.f1281b = kVarA;
        AlertController$RecycleListView alertController$RecycleListView = kVarA.f708d.f658g;
        alertController$RecycleListView.setTextDirection(i2);
        alertController$RecycleListView.setTextAlignment(i3);
        this.f1281b.show();
    }

    @Override // j.q0
    public final void dismiss() {
        d.k kVar = this.f1281b;
        if (kVar != null) {
            kVar.dismiss();
            this.f1281b = null;
        }
    }

    @Override // j.q0
    public final int g() {
        return 0;
    }

    @Override // j.q0
    public final Drawable h() {
        return null;
    }

    @Override // j.q0
    public final CharSequence i() {
        return this.f1283d;
    }

    @Override // j.q0
    public final void l(CharSequence charSequence) {
        this.f1283d = charSequence;
    }

    @Override // j.q0
    public final void m(Drawable drawable) {
        Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
    }

    @Override // j.q0
    public final void n(int i2) {
        Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
    }

    @Override // j.q0
    public final void o(ListAdapter listAdapter) {
        this.f1282c = listAdapter;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i2) {
        r0 r0Var = this.f1284e;
        r0Var.setSelection(i2);
        if (r0Var.getOnItemClickListener() != null) {
            r0Var.performItemClick(null, i2, this.f1282c.getItemId(i2));
        }
        dismiss();
    }

    @Override // j.q0
    public final void p(int i2) {
        Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
    }
}
