package com.example.ccc.ui.tcs

import android.os.Bundle
import android.text.Html.fromHtml
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ccc.R

class GeneralInfo:Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_general_info,container,false)

        val sc = fromHtml("<h1 style='margin-top:12.0pt;margin-right:0in;margin-bottom:.0001pt;margin-left:0in;font-size:21px;font-family:\"Calibri Light\",sans-serif;color:#2F5496;font-weight:normal;'><span style=\"color:windowtext;\">General</span></h1>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>What is app is a mobile application designed to enhance campus safety, accessibility, and community engagement.</p>\n" +
                "<h1 style='margin-top:12.0pt;margin-right:0in;margin-bottom:.0001pt;margin-left:0in;font-size:21px;font-family:\"Calibri Light\",sans-serif;color:#2F5496;font-weight:normal;'><span style=\"color:windowtext;\">&nbsp;How do I download/install?</span></h1>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>The app is available for Android devices. It would be made available to as soon as we launch to Download from the App Store/Google Play Store.</p>\n" +
                "<h1 style='margin-top:12.0pt;margin-right:0in;margin-bottom:.0001pt;margin-left:0in;font-size:21px;font-family:\"Calibri Light\",sans-serif;color:#2F5496;font-weight:normal;'><span style=\"color:windowtext;\">Login/Registration</span></h1>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>How do I log in?</p>\n" +
                "<ul style=\"list-style-type: disc;\">\n" +
                "    <li>Use your university-issued student email and create your own password.</li>\n" +
                "    <li>I forgot my password. What do I do?</li>\n" +
                "</ul>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>Tap &quot;Forgot Password&quot; and follow prompts.</p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>Community Messenger</p>\n" +
                "<div style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>\n" +
                "    <ul style=\"margin-bottom:0in;list-style-type: disc;\">\n" +
                "        <li style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>Receive important campus updates and notifications. Send messages to fellow students, faculty, or staff.</li>\n" +
                "    </ul>\n" +
                "</div>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>Campus Maps</p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>Navigate the campus with ease using our interactive maps, featuring:</p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>Building locations</p>\n" +
                "<ul style=\"list-style-type: disc;\">\n" +
                "    <li>Route directions</li>\n" +
                "    <li>Surrounding amenities</li>\n" +
                "    <li>Lost &amp; Found</li>\n" +
                "</ul>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>Report lost or found items on campus.</p>\n" +
                "<ul style=\"list-style-type: disc;\">\n" +
                "    <li>Post descriptions and locations</li>\n" +
                "    <li>Facilitate retrieval by owners</li>\n" +
                "    <li>Emergency</li>\n" +
                "</ul>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>Access critical emergency contact information and resources.</p>\n" +
                "<ul style=\"list-style-type: disc;\">\n" +
                "    <li>Contact details for campus security, medical services, and more</li>\n" +
                "    <li>Quick dialing for urgent situations</li>\n" +
                "    <li>Report an Incident</li>\n" +
                "</ul>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>Notify campus authorities about:</p>\n" +
                "<ul style=\"list-style-type: disc;\">\n" +
                "    <li>Safety concerns</li>\n" +
                "    <li>Maintenance issues</li>\n" +
                "    <li>Other incidents</li>\n" +
                "</ul>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>Manage your account settings.</p>\n" +
                "<ul style=\"list-style-type: disc;\">\n" +
                "    <li>Edit profile information</li>\n" +
                "    <li>Customize preferences</li>\n" +
                "    <li>Access general settings</li>\n" +
                "</ul>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>&nbsp;</p>\n" +
                "<h1 style='margin-top:12.0pt;margin-right:0in;margin-bottom:.0001pt;margin-left:0in;font-size:21px;font-family:\"Calibri Light\",sans-serif;color:#2F5496;font-weight:normal;'><span style=\"color:windowtext;\">Features</span></h1>\n" +
                "<ul style=\"list-style-type: disc;\">\n" +
                "    <li>Campus map</li>\n" +
                "    <li>Emergency alerts</li>\n" +
                "    <li>Event calendar</li>\n" +
                "    <li>Reporting incidents</li>\n" +
                "    <li>Push notifications</li>\n" +
                "    <li>Emergency numbers</li>\n" +
                "</ul>\n" +
                "<h1 style='margin-top:12.0pt;margin-right:0in;margin-bottom:.0001pt;margin-left:0in;font-size:21px;font-family:\"Calibri Light\",sans-serif;color:#2F5496;font-weight:normal;'><span style=\"color:windowtext;\">Incident Reporting</span></h1>\n" +
                "<ol style=\"list-style-type: decimal;\">\n" +
                "    <li>How do I report an incident?</li>\n" +
                "</ol>\n" +
                "<ul style=\"list-style-type: disc;margin-left: 0.25in;\">\n" +
                "    <li>Tap &quot;Report Incident&quot; and select category (e.g., safety, maintenance).</li>\n" +
                "</ul>\n" +
                "<ol start=\"2\" style=\"list-style-type: decimal;\">\n" +
                "    <li>What happens after I report an incident?</li>\n" +
                "</ol>\n" +
                "<ul style=\"list-style-type: disc;margin-left: 0.25in;\">\n" +
                "    <li>Reports are sent to campus authorities for review and action.</li>\n" +
                "</ul>\n" +
                "<h1 style='margin-top:12.0pt;margin-right:0in;margin-bottom:.0001pt;margin-left:0in;font-size:21px;font-family:\"Calibri Light\",sans-serif;color:#2F5496;font-weight:normal;'><span style=\"color:windowtext;\">Technical Issues</span></h1>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>If the app is not responding try the following</p>\n" +
                "<ul style=\"list-style-type: disc;\">\n" +
                "    <li>Closing/reopening app</li>\n" +
                "    <li>Checking internet connection</li>\n" +
                "    <li>Updating app version</li>\n" +
                "    <li>Contacting support ([support email/phone number])</li>\n" +
                "</ul>\n" +
                "<h1 style='margin-top:12.0pt;margin-right:0in;margin-bottom:.0001pt;margin-left:0in;font-size:21px;font-family:\"Calibri Light\",sans-serif;color:#2F5496;font-weight:normal;'><span style=\"color:windowtext;\">Security</span></h1>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>Your data is secured and it will not be share by e third party and it also includes five logins the app will automatically lock it self. It has followed industry-standard encryption and privacy protocols.</p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'><span style='font-size:21px;font-family:\"Calibri Light\",sans-serif;font-family:\"Times New Roman\";'>Contact</span></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>202047606@keyaka.ul.ac.za</p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>0714489187</p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>&nbsp;</p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>&nbsp;</p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>Additional Resources</p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>&nbsp;</p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>&nbsp;</p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>- University policy manual</p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>- Campus safety resources</p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;font-size:11.0pt;font-family:\"Calibri\",sans-serif;'>- Student conduct guidelines</p>")

        val txt = view.findViewById<TextView>(R.id.general_txt)

        txt.setText(sc)
        return view
    }
}