package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.activity.h;
import androidx.lifecycle.g;
import androidx.lifecycle.j;
import androidx.lifecycle.l;
import androidx.lifecycle.t;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
final class Recreator implements j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f461a;

    public Recreator(e eVar) {
        this.f461a = eVar;
    }

    @Override // androidx.lifecycle.j
    public final void a(l lVar, g gVar) {
        if (gVar != g.ON_CREATE) {
            throw new AssertionError("Next event must be ON_CREATE");
        }
        lVar.h().X(this);
        e eVar = this.f461a;
        Bundle bundleA = ((h) eVar).f65e.f469b.a("androidx.savedstate.Restarter");
        if (bundleA == null) {
            return;
        }
        ArrayList<String> stringArrayList = bundleA.getStringArrayList("classes_to_restore");
        if (stringArrayList == null) {
            throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
        }
        for (String str : stringArrayList) {
            try {
                Class<? extends U> clsAsSubclass = Class.forName(str, false, Recreator.class.getClassLoader()).asSubclass(a.class);
                try {
                    Constructor declaredConstructor = clsAsSubclass.getDeclaredConstructor(new Class[0]);
                    declaredConstructor.setAccessible(true);
                    try {
                        ((t) ((a) declaredConstructor.newInstance(new Object[0]))).a(eVar);
                    } catch (Exception e2) {
                        throw new RuntimeException("Failed to instantiate " + str, e2);
                    }
                } catch (NoSuchMethodException e3) {
                    throw new IllegalStateException("Class" + clsAsSubclass.getSimpleName() + " must have default constructor in order to be automatically recreated", e3);
                }
            } catch (ClassNotFoundException e4) {
                throw new RuntimeException(androidx.activity.c.a("Class ", str, " wasn't found"), e4);
            }
        }
    }
}
