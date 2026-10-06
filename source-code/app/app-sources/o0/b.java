package o0;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.snapay.app.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1749a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f1750b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g f1751c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final TextView f1752d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public i f1753e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public d f1754f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f1755g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f1756h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f1757i;

    public b(Context context, LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView) {
        this.f1749a = 0;
        this.f1750b = context;
        this.f1751c = g.a(context);
        this.f1755g = linearLayout;
        this.f1756h = linearLayout2;
        this.f1752d = textView;
        c();
    }

    public static void a(b bVar) {
        g gVar = bVar.f1751c;
        gVar.f1779a.edit().putString("preferred_bank", "NONE").apply();
        gVar.f1785g = "NONE";
        gVar.f1779a.edit().putString("custom_bank_name", "").apply();
        gVar.f1786h = "";
        bVar.f1757i = "";
        bVar.f1752d.setText("NONE");
        bVar.h("NONE");
        d dVar = bVar.f1754f;
        if (dVar != null) {
            dVar.a();
        }
    }

    public static String b(String str) {
        String upperCase = str.toUpperCase();
        upperCase.getClass();
        switch (upperCase) {
            case "CB":
                return "Canara Bank";
            case "IB":
                return "Indian Bank";
            case "BOB":
                return "Bank of Baroda";
            case "BOI":
                return "Bank of India";
            case "BOM":
                return "Bank of Maharashtra";
            case "CBI":
                return "Central Bank of India";
            case "IOB":
                return "Indian Overseas Bank";
            case "PNB":
                return "Punjab National Bank";
            case "PSB":
                return "Punjab & Sind Bank";
            case "SBI":
                return "State Bank of India";
            case "UBI":
                return "Union Bank of India";
            case "UCO":
                return "UCO Bank";
            case "AXIS":
                return "Axis Bank";
            case "HDFC":
                return "HDFC Bank";
            case "ICICI":
                return "ICICI Bank";
            default:
                return str;
        }
    }

    public static void g(LinearLayout linearLayout, int i2) {
        for (int i3 = 0; i3 < linearLayout.getChildCount(); i3++) {
            View childAt = linearLayout.getChildAt(i3);
            if (childAt instanceof TextView) {
                ((TextView) childAt).setTextColor(i2);
            }
        }
    }

    public final void c() {
        TextView textView;
        switch (this.f1749a) {
            case 0:
                i(this.f1751c.f1782d);
                final int i2 = 0;
                ((LinearLayout) this.f1755g).setOnClickListener(new View.OnClickListener(this) { // from class: o0.a

                    /* JADX INFO: renamed from: c, reason: collision with root package name */
                    public final /* synthetic */ b f1748c;

                    {
                        this.f1748c = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        int i3 = i2;
                        b bVar = this.f1748c;
                        switch (i3) {
                            case 0:
                                bVar.f("UPI_AUTOPAY");
                                break;
                            default:
                                bVar.f("QR_SCANNER");
                                break;
                        }
                    }
                });
                final int i3 = 1;
                ((LinearLayout) this.f1756h).setOnClickListener(new View.OnClickListener(this) { // from class: o0.a

                    /* JADX INFO: renamed from: c, reason: collision with root package name */
                    public final /* synthetic */ b f1748c;

                    {
                        this.f1748c = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        int i4 = i3;
                        b bVar = this.f1748c;
                        switch (i4) {
                            case 0:
                                bVar.f("UPI_AUTOPAY");
                                break;
                            default:
                                bVar.f("QR_SCANNER");
                                break;
                        }
                    }
                });
                break;
            default:
                String str = this.f1751c.f1785g;
                String str2 = this.f1751c.f1786h;
                String strB = "NONE";
                if (!str.equals("NONE")) {
                    if (!str.equals("OTHER") || str2.isEmpty()) {
                        textView = this.f1752d;
                        strB = b(str);
                    } else {
                        this.f1752d.setText("OTHER (" + str2 + ")");
                        this.f1757i = str2;
                    }
                    h(str);
                } else {
                    textView = this.f1752d;
                }
                textView.setText(strB);
                h(str);
                break;
        }
    }

    public final void d(String str) {
        TextView textView;
        String strB;
        this.f1756h = str;
        if (str.equals("UPI_AUTOPAY")) {
            this.f1752d.setText("NONE");
            h("NONE");
            for (Button button : (List) this.f1755g) {
                button.setEnabled(false);
                button.setAlpha(0.5f);
            }
            return;
        }
        String str2 = this.f1751c.f1785g;
        String str3 = this.f1751c.f1786h;
        if (str2.equals("NONE")) {
            this.f1752d.setText("NONE");
        } else {
            if (!str2.equals("OTHER") || str3.isEmpty()) {
                textView = this.f1752d;
                strB = b(str2);
            } else {
                this.f1757i = str3;
                textView = this.f1752d;
                strB = "OTHER (" + ((String) this.f1757i) + ")";
            }
            textView.setText(strB);
        }
        h(str2);
        for (Button button2 : (List) this.f1755g) {
            button2.setEnabled(true);
            button2.setAlpha(1.0f);
        }
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0069  */
    /* JADX WARN: Code duplicated, block: B:18:? A[RETURN, SYNTHETIC] */
    public final void e(String str) {
        TextView textView;
        String strB;
        d dVar;
        StringBuilder sb;
        g gVar = this.f1751c;
        gVar.f1779a.edit().putString("preferred_bank", str).apply();
        gVar.f1785g = str;
        if (str.equals("OTHER")) {
            if (((String) this.f1757i).isEmpty()) {
                String str2 = this.f1751c.f1786h;
                if (str2.isEmpty()) {
                    this.f1752d.setText("OTHER");
                } else {
                    this.f1757i = str2;
                    textView = this.f1752d;
                    sb = new StringBuilder("OTHER (");
                }
                h(str);
                dVar = this.f1754f;
                if (dVar != null) {
                    dVar.a();
                }
            }
            textView = this.f1752d;
            sb = new StringBuilder("OTHER (");
            sb.append((String) this.f1757i);
            sb.append(")");
            strB = sb.toString();
        } else {
            textView = this.f1752d;
            strB = b(str);
        }
        textView.setText(strB);
        h(str);
        dVar = this.f1754f;
        if (dVar != null) {
            dVar.a();
        }
    }

    public final void f(String str) {
        g gVar = this.f1751c;
        gVar.f1779a.edit().putString("app_mode", str).apply();
        gVar.f1782d = str;
        i(str);
        b bVar = (b) this.f1757i;
        if (bVar != null) {
            bVar.d(str);
        }
        i iVar = this.f1753e;
        if (iVar != null) {
            iVar.b(str);
        }
        d dVar = this.f1754f;
        if (dVar != null) {
            dVar.a();
        }
    }

    public final void h(String str) {
        for (Button button : (List) this.f1755g) {
            button.setBackgroundResource(button.getText().toString().equalsIgnoreCase(str) ? R.drawable.btn_bank_selected : R.drawable.btn_bank);
            button.setTextColor(Color.parseColor("#4C3FC1"));
        }
    }

    public final void i(String str) {
        int i2;
        boolean zEquals = str.equals("UPI_AUTOPAY");
        TextView textView = this.f1752d;
        Object obj = this.f1755g;
        if (zEquals) {
            LinearLayout linearLayout = (LinearLayout) obj;
            linearLayout.setBackgroundResource(R.drawable.btn_upi_autopay_selected);
            g(linearLayout, Color.parseColor("#4C3FC1"));
            ((LinearLayout) this.f1756h).setBackgroundResource(R.drawable.btn_qr_scanner);
            g((LinearLayout) this.f1756h, Color.parseColor("#1A1446"));
            if (textView == null) {
                return;
            } else {
                i2 = 8;
            }
        } else {
            ((LinearLayout) this.f1756h).setBackgroundResource(R.drawable.btn_qr_scanner_selected);
            g((LinearLayout) this.f1756h, Color.parseColor("#4C3FC1"));
            LinearLayout linearLayout2 = (LinearLayout) obj;
            linearLayout2.setBackgroundResource(R.drawable.btn_upi_autopay);
            g(linearLayout2, Color.parseColor("#1A1446"));
            if (textView == null) {
                return;
            } else {
                i2 = 0;
            }
        }
        textView.setVisibility(i2);
    }

    public b(Context context, TextView textView) {
        this.f1749a = 1;
        this.f1756h = "QR_SCANNER";
        this.f1757i = "";
        this.f1750b = context;
        g gVarA = g.a(context);
        this.f1751c = gVarA;
        this.f1752d = textView;
        this.f1755g = new ArrayList();
        this.f1756h = gVarA.f1782d;
    }
}
