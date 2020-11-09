package kr.co.douchgosum.android.coinradar.customviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.douchgosum.android.coinradar.R


class BottomSheetDialog: BottomSheetDialogFragment() {
    fun getInstance(): BottomSheetDialog? {
        return BottomSheetDialog()
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.bottom_sheet_dialog, container, false)
        return view
    }
}