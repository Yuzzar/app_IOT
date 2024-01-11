import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app_projectiot.databinding.FragmentFHomeBinding
import com.example.app_projectiot.informasi_pompa
import com.example.app_projectiot.informasi_suhu
import com.example.app_projectiot.informasi_toren

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FHome : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentFHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.torenBut.setOnClickListener {
            val intent = Intent(requireActivity(), informasi_toren::class.java)
            requireActivity().startActivity(intent)
        }

        binding.pompaBut.setOnClickListener {
            val intent = Intent(requireActivity(), informasi_pompa::class.java)
            requireActivity().startActivity(intent)
        }

        binding.suhuBut.setOnClickListener {
            val intent = Intent(requireActivity(), informasi_suhu::class.java)
            requireActivity().startActivity(intent)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
