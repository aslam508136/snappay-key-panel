package o0;

import android.content.Context;
import android.graphics.Color;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import j.d2;
import j.f2;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1789a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g f1790b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final EditText f1791c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Button f1792d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final TextView f1793e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final TextView f1794f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ImageButton f1795g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f1796h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1797i = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f1798j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public d f1799k;

    public i(Context context, EditText editText, Button button, TextView textView, TextView textView2, ImageButton imageButton) {
        this.f1796h = "QR_SCANNER";
        this.f1798j = false;
        this.f1789a = context;
        g gVarA = g.a(context);
        this.f1790b = gVarA;
        this.f1791c = editText;
        this.f1792d = button;
        this.f1793e = textView;
        this.f1794f = textView2;
        this.f1795g = imageButton;
        this.f1796h = gVarA.f1782d;
        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        a();
        c();
        if (gVarA.b()) {
            this.f1798j = true;
            editText.setText("UPI_AUTOPAY".equals(gVarA.f1782d) ? gVarA.f1783e : gVarA.f1784f);
            textView.setVisibility(0);
            this.f1798j = false;
        }
        imageButton.setOnClickListener(new h(this, 0));
        editText.setOnFocusChangeListener(new f2(this, 1));
        editText.addTextChangedListener(new d2(this, 2));
        button.setOnClickListener(new h(this, 1));
    }

    public final void a() {
        EditText editText = this.f1791c;
        editText.setEnabled(true);
        editText.setHint("Enter PIN");
        editText.setHintTextColor(Color.parseColor("#999999"));
        this.f1794f.setVisibility(8);
        c();
    }

    public final void b(String str) {
        this.f1796h = str;
        this.f1798j = true;
        g gVar = this.f1790b;
        String str2 = "UPI_AUTOPAY".equals(gVar.f1782d) ? gVar.f1783e : gVar.f1784f;
        this.f1791c.setText(str2);
        boolean zIsEmpty = str2.isEmpty();
        TextView textView = this.f1793e;
        if (zIsEmpty) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
        this.f1798j = false;
        a();
        c();
    }

    public final void c() {
        String str;
        Button button = this.f1792d;
        button.setVisibility(0);
        boolean zB = this.f1790b.b();
        EditText editText = this.f1791c;
        if (zB) {
            button.setText("UPDATE");
            str = "Update PIN";
        } else {
            button.setText("SAVE");
            str = "Enter PIN";
        }
        editText.setHint(str);
        editText.setHintTextColor(Color.parseColor("#999999"));
        button.setEnabled(false);
        button.setTextColor(Color.parseColor("#999999"));
    }
}
