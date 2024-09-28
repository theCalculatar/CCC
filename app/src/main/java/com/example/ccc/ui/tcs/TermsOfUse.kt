package com.example.ccc.ui.tcs

import android.os.Bundle
import android.text.Html.fromHtml
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ccc.R

class TermsOfUse:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_terms_of_use,container, false)
        val sc = fromHtml("<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><strong><span style='font-size:24px;font-family:\"Times New Roman\";'>Terms of&nbsp;</span></strong><span style='font-size:24px;font-family:\"Times New Roman\";'>Service</span></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><strong><span style='font-size:18px;font-family:\"Times New Roman\";'>1. Acceptance of Terms</span></strong></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:\"Times New Roman\";'>By accessing or using the Campus Control Canter application you agree to comply with and be bound by these Terms of Service. If you do not agree to these terms, you may not use the App. The University reserves the right to update or modify these terms at anytime.</span></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><strong><span style='font-size:18px;font-family:\"Times New Roman\";'>2. Purpose of the App</span></strong></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:\"Times New Roman\";'>The App is designed to enhance campus security and streamline administrative processes for students. It provides the following services:</span></p>\n" +
                "<ul type=\"disc\" style=\"margin-bottom:0in;\">\n" +
                "    <li style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:     \"Times New Roman\";'>Management and reporting of lost or stolen student cards.</span></li>\n" +
                "    <li style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:     \"Times New Roman\";'>Issuing and managing digital pass-outs for access to campus facilities and labs.</span></li>\n" +
                "    <li style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:     \"Times New Roman\";'>Reporting and tracking lost belongings.</span></li>\n" +
                "    <li style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:     \"Times New Roman\";'>Communication of important student information, including test and exam venues.</span></li>\n" +
                "</ul>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><strong><span style='font-size:18px;font-family:\"Times New Roman\";'>3. Student Card Management</span></strong></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:\"Times New Roman\";'>The App allows students to report lost student cards and manage replacements remotely. You are responsible for ensuring the accuracy of the information you provide. The university reserves the right to verify all reported losses before issuing a new card.</span></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><strong><span style='font-size:18px;font-family:\"Times New Roman\";'>4. Pass-Out Management</span></strong></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:\"Times New Roman\";'>Students can manage and update their pass-out documents through the App, including changes in residency or device details. Pass-outs are required for entry to campus computer labs and access points. The information provided must be accurate, and the university will verify changes before approving updates.</span></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><strong><span style='font-size:18px;font-family:\"Times New Roman\";'>5. Theft Reporting</span></strong></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:\"Times New Roman\";'>The App provides a platform for reporting lost or stolen belongings such as laptops, cell phones, and external drives. The reported information will be stored in a central database to assist in the recovery of lost items. The App may notify users if lost belongings are found, but recovery is subject to verification by campus security.</span></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><strong><span style='font-size:18px;font-family:\"Times New Roman\";'>6. User Responsibilities</span></strong></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:\"Times New Roman\";'>By using the App, you agree to:</span></p>\n" +
                "<ul type=\"disc\" style=\"margin-bottom:0in;\">\n" +
                "    <li style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:     \"Times New Roman\";'>Provide accurate and up-to-date information for student card, pass-out, and theft reporting services.</span></li>\n" +
                "    <li style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:     \"Times New Roman\";'>Use the App in compliance with campus regulations and applicable laws.</span></li>\n" +
                "    <li style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:     \"Times New Roman\";'>Not misuse or abuse the App to submit false reports or fraudulent information.</span></li>\n" +
                "</ul>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><strong><span style='font-size:18px;font-family:\"Times New Roman\";'>7. Data Collection and Privacy</span></strong></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:\"Times New Roman\";'>The App collects personal information such as your student ID, contact details, and device information to facilitate services. The university will take reasonable measures to ensure the security of your data. By using the App, you consent to the collection and processing of your personal data in accordance with the university&rsquo;s privacy policy.</span></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><strong><span style='font-size:18px;font-family:\"Times New Roman\";'>8. Limitation of Liability</span></strong></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:\"Times New Roman\";'>The university is not responsible for:</span></p>\n" +
                "<ul type=\"disc\" style=\"margin-bottom:0in;\">\n" +
                "    <li style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:     \"Times New Roman\";'>Any delays or inaccuracies in the replacement of student cards or pass-outs.</span></li>\n" +
                "    <li style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:     \"Times New Roman\";'>Losses incurred due to the failure to recover stolen or lost belongings.</span></li>\n" +
                "    <li style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:     \"Times New Roman\";'>Unauthorized access to your data due to factors beyond the university&rsquo;s control (e.g., cyber-attacks).</span></li>\n" +
                "</ul>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><strong><span style='font-size:18px;font-family:\"Times New Roman\";'>9. Account Security</span></strong></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:\"Times New Roman\";'>You are responsible for maintaining the confidentiality of your account login details. The university is not liable for any unauthorized access to your account due to the loss or sharing of your credentials.</span></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><strong><span style='font-size:18px;font-family:\"Times New Roman\";'>10. Modifications to the Service</span></strong></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:\"Times New Roman\";'>The university may modify, suspend, or discontinue any aspect of the App without notice, including the availability of any feature or content. The university is not liable to users for any modifications or discontinuation of services.</span></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><strong><span style='font-size:18px;font-family:\"Times New Roman\";'>11. Termination</span></strong></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:\"Times New Roman\";'>The university reserves the right to suspend or terminate your access to the App if you violate these Terms of Service or engage in any conduct that disrupts campus security.</span></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><strong><span style='font-size:18px;font-family:\"Times New Roman\";'>12. Contact Information</span></strong></p>\n" +
                "<p style='margin-top:0in;margin-right:0in;margin-bottom:8.0pt;margin-left:0in;line-height:normal;font-size:16px;font-family:\"Aptos\",sans-serif;'><span style='font-family:\"Times New Roman\";'>For any questions or concerns regarding these Terms of Service, please contact the university&rsquo;s campus security department at <strong>0152682823</strong></span></p>")
        val txt = view.findViewById<TextView>(R.id.t_n_cs)
        txt.setText(sc)

        return view
    }
}