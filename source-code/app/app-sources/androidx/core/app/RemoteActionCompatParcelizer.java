package androidx.core.app;

import android.app.PendingIntent;
import android.os.Parcel;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import h0.a;
import h0.b;
import h0.c;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        c cVarH = remoteActionCompat.f242a;
        if (aVar.e(1)) {
            cVarH = aVar.h();
        }
        remoteActionCompat.f242a = (IconCompat) cVarH;
        CharSequence charSequence = remoteActionCompat.f243b;
        if (aVar.e(2)) {
            charSequence = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((b) aVar).f961e);
        }
        remoteActionCompat.f243b = charSequence;
        CharSequence charSequence2 = remoteActionCompat.f244c;
        if (aVar.e(3)) {
            charSequence2 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((b) aVar).f961e);
        }
        remoteActionCompat.f244c = charSequence2;
        remoteActionCompat.f245d = (PendingIntent) aVar.g(remoteActionCompat.f245d, 4);
        boolean z2 = remoteActionCompat.f246e;
        if (aVar.e(5)) {
            z2 = ((b) aVar).f961e.readInt() != 0;
        }
        remoteActionCompat.f246e = z2;
        boolean z3 = remoteActionCompat.f247f;
        if (aVar.e(6)) {
            z3 = ((b) aVar).f961e.readInt() != 0;
        }
        remoteActionCompat.f247f = z3;
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, a aVar) {
        aVar.getClass();
        IconCompat iconCompat = remoteActionCompat.f242a;
        aVar.i(1);
        aVar.j(iconCompat);
        CharSequence charSequence = remoteActionCompat.f243b;
        aVar.i(2);
        Parcel parcel = ((b) aVar).f961e;
        TextUtils.writeToParcel(charSequence, parcel, 0);
        CharSequence charSequence2 = remoteActionCompat.f244c;
        aVar.i(3);
        TextUtils.writeToParcel(charSequence2, parcel, 0);
        PendingIntent pendingIntent = remoteActionCompat.f245d;
        aVar.i(4);
        parcel.writeParcelable(pendingIntent, 0);
        boolean z2 = remoteActionCompat.f246e;
        aVar.i(5);
        parcel.writeInt(z2 ? 1 : 0);
        boolean z3 = remoteActionCompat.f247f;
        aVar.i(6);
        parcel.writeInt(z3 ? 1 : 0);
    }
}
