package d;

import android.content.Context;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.os.PowerManager;
import android.util.Log;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public final class v extends x {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f724c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ a0 f725d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f726e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(a0 a0Var, Context context) {
        super(a0Var);
        this.f725d = a0Var;
        this.f726e = (PowerManager) context.getApplicationContext().getSystemService("power");
    }

    @Override // d.x
    public final IntentFilter b() {
        switch (this.f724c) {
            case 0:
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
                return intentFilter;
            default:
                IntentFilter intentFilter2 = new IntentFilter();
                intentFilter2.addAction("android.intent.action.TIME_SET");
                intentFilter2.addAction("android.intent.action.TIMEZONE_CHANGED");
                intentFilter2.addAction("android.intent.action.TIME_TICK");
                return intentFilter2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:75:0x012e A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Code duplicated, block: B:82:? A[RETURN, SYNTHETIC] */
    @Override // d.x
    public final int c() {
        Location location;
        boolean z2;
        long j2;
        f0 f0Var;
        Location lastKnownLocation;
        int i2 = this.f724c;
        Object obj = this.f726e;
        switch (i2) {
            case 0:
                return ((PowerManager) obj).isPowerSaveMode() ? 2 : 1;
            default:
                m0.a aVar = (m0.a) obj;
                f0 f0Var2 = (f0) aVar.f1644c;
                if (!(f0Var2.f643b > System.currentTimeMillis())) {
                    Context context = (Context) aVar.f1642a;
                    int iH = androidx.lifecycle.i.h(context, "android.permission.ACCESS_COARSE_LOCATION");
                    Location lastKnownLocation2 = null;
                    Object obj2 = aVar.f1643b;
                    if (iH == 0) {
                        try {
                            lastKnownLocation = ((LocationManager) obj2).isProviderEnabled("network") ? ((LocationManager) obj2).getLastKnownLocation("network") : null;
                        } catch (Exception e2) {
                            Log.d("TwilightManager", "Failed to get last known location", e2);
                        }
                        location = lastKnownLocation;
                        break;
                    } else {
                        location = null;
                    }
                    if (androidx.lifecycle.i.h(context, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                        try {
                            if (((LocationManager) obj2).isProviderEnabled("gps")) {
                                lastKnownLocation2 = ((LocationManager) obj2).getLastKnownLocation("gps");
                            }
                        } catch (Exception e3) {
                            Log.d("TwilightManager", "Failed to get last known location", e3);
                        }
                    }
                    if (lastKnownLocation2 == null || location == null ? lastKnownLocation2 != null : lastKnownLocation2.getTime() > location.getTime()) {
                        location = lastKnownLocation2;
                    }
                    if (location != null) {
                        f0 f0Var3 = (f0) aVar.f1644c;
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        if (e0.f624d == null) {
                            e0.f624d = new e0();
                        }
                        e0 e0Var = e0.f624d;
                        e0Var.a(jCurrentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
                        e0Var.a(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
                        boolean z3 = e0Var.f627c == 1;
                        long j3 = e0Var.f626b;
                        long j4 = e0Var.f625a;
                        e0Var.a(86400000 + jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
                        long j5 = e0Var.f626b;
                        if (j3 == -1 || j4 == -1) {
                            j2 = jCurrentTimeMillis + 43200000;
                        } else {
                            j2 = (jCurrentTimeMillis > j4 ? j5 + 0 : jCurrentTimeMillis > j3 ? j4 + 0 : j3 + 0) + 60000;
                        }
                        f0Var3.f642a = z3;
                        f0Var3.f643b = j2;
                        f0Var = f0Var2;
                    } else {
                        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
                        int i3 = Calendar.getInstance().get(11);
                        z2 = i3 < 6 || i3 >= 22;
                    }
                    if (z2) {
                        return 2;
                    }
                    return 1;
                }
                f0Var = f0Var2;
                z2 = f0Var.f642a;
                if (z2) {
                    return 2;
                }
                return 1;
        }
    }

    @Override // d.x
    public final void d() {
        int i2 = this.f724c;
        a0 a0Var = this.f725d;
        switch (i2) {
            case 0:
                a0Var.j(true);
                break;
            default:
                a0Var.j(true);
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(a0 a0Var, m0.a aVar) {
        super(a0Var);
        this.f725d = a0Var;
        this.f726e = aVar;
    }
}
