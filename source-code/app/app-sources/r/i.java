package r;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.ParcelFileDescriptor;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class i extends h.a {
    public i() {
        super(0);
    }

    @Override // h.a
    public final Typeface f(Context context, q.c cVar, Resources resources, int i2) {
        try {
            q.d[] dVarArr = cVar.f1840a;
            int length = dVarArr.length;
            FontFamily.Builder builder = null;
            int i3 = 0;
            while (true) {
                int i4 = 1;
                if (i3 >= length) {
                    break;
                }
                q.d dVar = dVarArr[i3];
                try {
                    Font.Builder weight = new Font.Builder(resources, dVar.f1846f).setWeight(dVar.f1842b);
                    if (!dVar.f1843c) {
                        i4 = 0;
                    }
                    Font fontBuild = weight.setSlant(i4).setTtcIndex(dVar.f1845e).setFontVariationSettings(dVar.f1844d).build();
                    if (builder == null) {
                        builder = new FontFamily.Builder(fontBuild);
                    } else {
                        builder.addFont(fontBuild);
                    }
                } catch (IOException unused) {
                }
                i3++;
            }
            if (builder == null) {
                return null;
            }
            return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle((i2 & 1) != 0 ? 700 : 400, (i2 & 2) != 0 ? 1 : 0)).build();
        } catch (Exception unused2) {
            return null;
        }
    }

    @Override // h.a
    public final Typeface g(Context context, u.h[] hVarArr, int i2) {
        ContentResolver contentResolver = context.getContentResolver();
        try {
            int length = hVarArr.length;
            FontFamily.Builder builder = null;
            int i3 = 0;
            while (true) {
                int i4 = 1;
                if (i3 >= length) {
                    if (builder == null) {
                        return null;
                    }
                    return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle((i2 & 1) != 0 ? 700 : 400, (i2 & 2) != 0 ? 1 : 0)).build();
                }
                u.h hVar = hVarArr[i3];
                try {
                    ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = contentResolver.openFileDescriptor(hVar.f1945a, "r", null);
                    if (parcelFileDescriptorOpenFileDescriptor == null) {
                        if (parcelFileDescriptorOpenFileDescriptor != null) {
                        }
                        i3++;
                    } else {
                        try {
                            Font.Builder weight = new Font.Builder(parcelFileDescriptorOpenFileDescriptor).setWeight(hVar.f1947c);
                            if (!hVar.f1948d) {
                                i4 = 0;
                            }
                            Font fontBuild = weight.setSlant(i4).setTtcIndex(hVar.f1946b).build();
                            if (builder == null) {
                                builder = new FontFamily.Builder(fontBuild);
                            } else {
                                builder.addFont(fontBuild);
                            }
                        } catch (Throwable th) {
                            try {
                                parcelFileDescriptorOpenFileDescriptor.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    }
                    parcelFileDescriptorOpenFileDescriptor.close();
                } catch (IOException unused) {
                }
                i3++;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    @Override // h.a
    public final Typeface h(Context context, InputStream inputStream) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override // h.a
    public final Typeface i(Context context, Resources resources, int i2, String str, int i3) {
        try {
            Font fontBuild = new Font.Builder(resources, i2).build();
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(fontBuild).build()).setStyle(fontBuild.getStyle()).build();
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // h.a
    public final u.h l(int i2, u.h[] hVarArr) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }
}
