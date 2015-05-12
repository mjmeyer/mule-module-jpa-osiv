package net.deepvoodoo.mule.modules.JpaOpenSessionInView;

import java.io.Serializable;

public interface CompletionCallback {
	public void onComplete(Serializable payload);
}
