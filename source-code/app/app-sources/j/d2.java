package j;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;
import com.snapay.app.LoginActivity;

/* JADX INFO: loaded from: classes.dex */
public final class d2 implements TextWatcher {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f1218b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f1219c;

    public /* synthetic */ d2(Object obj, int i2) {
        this.f1218b = i2;
        this.f1219c = obj;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        String str;
        int i5 = 0;
        int i6 = this.f1218b;
        Object obj = this.f1219c;
        switch (i6) {
            case 0:
                SearchView searchView = (SearchView) obj;
                Editable text = searchView.f196q.getText();
                searchView.W = text;
                boolean z2 = !TextUtils.isEmpty(text);
                searchView.w(z2);
                boolean z3 = !z2;
                if (searchView.V && !searchView.O && z3) {
                    searchView.f201v.setVisibility(8);
                } else {
                    i5 = 8;
                }
                searchView.f203x.setVisibility(i5);
                searchView.s();
                searchView.v();
                charSequence.toString();
                searchView.getClass();
                break;
            case 1:
                int i7 = LoginActivity.f527x;
                ((LoginActivity) obj).f531r.setVisibility(8);
                break;
            default:
                o0.i iVar = (o0.i) obj;
                if (!iVar.f1798j) {
                    int length = charSequence.length();
                    Button button = iVar.f1792d;
                    if (length == 4 || length == 6) {
                        button.setVisibility(0);
                        button.setEnabled(true);
                        str = "#FF0000";
                    } else {
                        button.setVisibility(0);
                        button.setEnabled(false);
                        str = "#999999";
                    }
                    button.setTextColor(Color.parseColor(str));
                    break;
                }
                break;
        }
    }
}
