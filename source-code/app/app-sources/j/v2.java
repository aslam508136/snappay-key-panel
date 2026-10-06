package j;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class v2 implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f1471b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f1472c;

    public /* synthetic */ v2(Object obj, int i2) {
        this.f1471b = i2;
        this.f1472c = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.f1471b) {
            case 0:
                w2 w2Var = ((Toolbar) this.f1472c).K;
                i.q qVar = w2Var != null ? w2Var.f1485c : null;
                if (qVar != null) {
                    qVar.collapseActionView();
                }
                break;
            default:
                if (!((String) ((o0.b) this.f1472c).f1756h).equals("UPI_AUTOPAY")) {
                    String string = ((Button) view).getText().toString();
                    if (!string.equalsIgnoreCase("OTHER")) {
                        if (string.equalsIgnoreCase(((o0.b) this.f1472c).f1751c.f1785g)) {
                            o0.b.a((o0.b) this.f1472c);
                        } else {
                            ((o0.b) this.f1472c).e(string);
                        }
                        o0.i iVar = ((o0.b) this.f1472c).f1753e;
                        if (iVar != null) {
                            iVar.a();
                        }
                    } else if (((o0.b) this.f1472c).f1751c.f1785g.equals("OTHER")) {
                        o0.b.a((o0.b) this.f1472c);
                    } else {
                        final o0.b bVar = (o0.b) this.f1472c;
                        String str = bVar.f1751c.f1785g;
                        String[] strArr = {"KOTAK - Kotak Mahindra Bank", "INDUSIND - IndusInd Bank", "YES - Yes Bank", "IDFC - IDFC First Bank", "FEDERAL - Federal Bank", "RBL - RBL Bank", "BANDHAN - Bandhan Bank", "SIB - South Indian Bank", "KVB - Karur Vysya Bank", "CUB - City Union Bank", "DCB - DCB Bank", "TMB - Tamilnad Mercantile Bank", "CSB - CSB Bank", "AU - AU Small Finance Bank", "UJJIVAN - Ujjivan Small Finance Bank", "EQUITAS - Equitas Small Finance Bank", "JANA - Jana Small Finance Bank", "SURYODAY - Suryoday Small Finance Bank", "ESAF - ESAF Small Finance Bank", "UTKARSH - Utkarsh Small Finance Bank", "NESFB - North East Small Finance Bank", "FINCARE - Fincare Small Finance Bank", "CAPITAL - Capital Small Finance Bank", "APB - Airtel Payments Bank", "IPPB - India Post Payments Bank", "PAYTM - Paytm Payments Bank", "FINO - Fino Payments Bank", "JIO - Jio Payments Bank", "CITI - Citibank", "HSBC - HSBC", "SCB - Standard Chartered Bank", "DB - Deutsche Bank", "BARCLAYS - Barclays Bank"};
                        ArrayList arrayList = new ArrayList();
                        for (int i2 = 0; i2 < 33; i2++) {
                            String str2 = strArr[i2];
                            if (!str2.split(" - ")[0].equalsIgnoreCase(str)) {
                                arrayList.add(str2);
                            }
                        }
                        final String[] strArr2 = (String[]) arrayList.toArray(new String[0]);
                        AlertDialog.Builder builder = new AlertDialog.Builder(bVar.f1750b);
                        builder.setTitle("Select Bank");
                        builder.setItems(strArr2, new DialogInterface.OnClickListener() { // from class: o0.e
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i3) {
                                b bVar2 = bVar;
                                bVar2.getClass();
                                String[] strArrSplit = strArr2[i3].split(" - ");
                                String str3 = strArrSplit.length > 1 ? strArrSplit[1] : strArrSplit[0];
                                bVar2.f1757i = str3;
                                g gVar = bVar2.f1751c;
                                gVar.f1779a.edit().putString("custom_bank_name", str3).apply();
                                gVar.f1786h = str3;
                                bVar2.e("OTHER");
                                i iVar2 = bVar2.f1753e;
                                if (iVar2 != null) {
                                    iVar2.a();
                                }
                            }
                        });
                        builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) null);
                        builder.show();
                    }
                    break;
                }
                break;
        }
    }
}
