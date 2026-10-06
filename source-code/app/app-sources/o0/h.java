package o0;

import android.graphics.Color;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.snapay.app.R;

/* JADX INFO: loaded from: classes.dex */
public final class h implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f1787b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ i f1788c;

    public /* synthetic */ h(i iVar, int i2) {
        this.f1787b = i2;
        this.f1788c = iVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        EditText editText;
        switch (this.f1787b) {
            case 0:
                i iVar = this.f1788c;
                iVar.f1798j = true;
                boolean z2 = iVar.f1797i;
                ImageButton imageButton = iVar.f1795g;
                EditText editText2 = iVar.f1791c;
                if (z2) {
                    editText2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imageButton.setImageResource(R.drawable.ic_eye_closed);
                    iVar.f1797i = false;
                } else {
                    editText2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageButton.setImageResource(R.drawable.ic_eye_open);
                    iVar.f1797i = true;
                }
                editText2.setSelection(editText2.getText().length());
                if (iVar.f1790b.b()) {
                    Button button = iVar.f1792d;
                    button.setEnabled(false);
                    button.setTextColor(Color.parseColor("#999999"));
                }
                iVar.f1798j = false;
                break;
            default:
                String string = this.f1788c.f1791c.getText().toString();
                if (string.length() == 4 || string.length() == 6) {
                    if (this.f1788c.f1796h.equals("UPI_AUTOPAY")) {
                        g gVar = this.f1788c.f1790b;
                        gVar.f1779a.edit().putString("upi_pin_upi_autopay", string).apply();
                        gVar.f1783e = string;
                    } else {
                        g gVar2 = this.f1788c.f1790b;
                        gVar2.f1779a.edit().putString("upi_pin_qr_scanner", string).apply();
                        gVar2.f1784f = string;
                    }
                    this.f1788c.c();
                    TextView textView = this.f1788c.f1793e;
                    textView.setVisibility(0);
                    textView.setText("✓ PIN SAVED");
                    i iVar2 = this.f1788c;
                    InputMethodManager inputMethodManager = (InputMethodManager) iVar2.f1789a.getSystemService("input_method");
                    if (inputMethodManager != null && (editText = iVar2.f1791c) != null) {
                        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    }
                    this.f1788c.f1791c.clearFocus();
                    d dVar = this.f1788c.f1799k;
                    if (dVar != null) {
                        dVar.a();
                    }
                }
                break;
        }
    }
}
