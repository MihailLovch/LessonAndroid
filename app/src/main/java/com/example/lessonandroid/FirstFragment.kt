package com.example.lessonandroid

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lessonandroid.databinding.FragmentFirstBinding
import org.w3c.dom.Text

class FirstFragment : Fragment(R.layout.fragment_first) {

    private val binding: FragmentFirstBinding by viewBinding(FragmentFirstBinding::bind)
    private val pattern = "\\+7 \\(9\\d{2}\\)-\\d{3}-\\d{2}-\\d{2}".toRegex()
    private val containerId: Int = R.id.main_fragment_container

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationBtn.isEnabled = false
        binding.someTextEt.isEnabled = false
        initListener()
    }

    private fun initListener() {
        with(binding) {
            phoneNumberEt.addTextChangedListener(object : TextWatcher {
                var count = 0
                override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(input: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(input: Editable) {
                    phoneNumberEt.removeTextChangedListener(this)
                    if (input.length == 1) {
                        input.insert(0, "+7 ${NUMBER_ZONE_FIRST_DELIMITER}9")
                    }
                    if (input.length == 4) {
                        input.replace(0, 4, "+7 ${NUMBER_ZONE_FIRST_DELIMITER}9")
                    }
                    if (input.length < count) {
                        navigationBtn.isEnabled = false
                    }
                    count = input.length
                    refactorInput(input)
                    enablingViews(input)
                    phoneNumberEt.addTextChangedListener(this)
                }

            })
            someTextEt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(input: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(input: Editable) {
                    someTextEt.removeTextChangedListener(this)
                    navigationBtn.isEnabled = checkText(input)
                    someTextEt.addTextChangedListener(this)
                }
            })
            navigationBtn.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(
                        containerId,
                        SecondFragment.getInstance(),
                        SecondFragment.SECOND_PAGE_FRAGMENT_TAG
                    )
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun refactorInput(input: Editable) {
        val clearedInput =StringBuilder(input.toString().replace(NUMBER_ZONE_SECOND_DELIMITER,"").replace(NUMBER_MAIN_DELIMITER,""))
        with(clearedInput) {
            if (length > 7) {
                insert(7, NUMBER_ZONE_SECOND_DELIMITER)
            }
            if (length >= 9) {
                insert(8, NUMBER_MAIN_DELIMITER)
            }
            if (length >= 13) {
                insert(12, NUMBER_MAIN_DELIMITER)
            }
            if (length >= 16) {
                insert(15, NUMBER_MAIN_DELIMITER)
            }
        }
        input.clear()
        input.insert(0,clearedInput.toString())
    }

    private fun enablingViews(input: Editable) {
        with(binding) {
            someTextEt.isEnabled = checkPhone(input)
            navigationBtn.isEnabled = checkPhone(input) && checkText(someTextEt.text)
        }
    }

    private fun checkPhone(input: Editable): Boolean {
        return pattern.matches(input.toString())
    }

    private fun checkText(input: Editable): Boolean {
        return input.toString().replace("\n", "").length >= 5
    }

    companion object {
        const val FIRST_PAGE_FRAGMENT_TAG = "FIRST_PAGE_FRAGMENT_TAG"
        const val NUMBER_ZONE_FIRST_DELIMITER = "("
        const val NUMBER_ZONE_SECOND_DELIMITER = ")"
        const val NUMBER_MAIN_DELIMITER = "-"

        fun getInstance() = FirstFragment()
    }
}