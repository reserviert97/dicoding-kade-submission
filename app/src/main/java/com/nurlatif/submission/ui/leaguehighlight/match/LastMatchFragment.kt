package com.nurlatif.submission.ui.leaguehighlight.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nurlatif.submission.R
import com.nurlatif.submission.network.Event
import kotlinx.android.synthetic.main.fragment_last_match.*
import org.jetbrains.anko.support.v4.toast


class LastMatchFragment : Fragment() {

    private var events: MutableList<Event> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        events.add(
            Event(
                "12", "HAHA", "ASA"
            )
        )
        events.add(
            Event(
                "12", "HAHA", "ASA"
            )
        )

        events.add(
            Event(
                "12", "HAHA", "ASA"
            )
        )

        events.add(
            Event(
                "12", "HAHA", "ASA"
            )
        )

        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        lastMatchRv.adapter = MatchAdapter(requireContext(), events) {
            toast("We Here")
        }
    }


}