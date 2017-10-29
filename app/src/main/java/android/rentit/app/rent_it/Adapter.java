package android.rentit.app.rent_it;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.graphics.Canvas.EdgeType.AA;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by vipul dimri on 26-07-2017.
 */

public class Adapter extends ArrayAdapter<Ad_details>


{


    Context ctx;

    public Adapter(Context context, List<Ad_details> com){
        super(context,0,com);
        this.ctx=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {



        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.adlistlook, parent, false);
        }




        Ad_details currentAndroidFlavor = getItem(position);


        TextView A = (TextView) listItemView.findViewById(R.id.price);
        final String price=currentAndroidFlavor.getprice();
        A.setText(currentAndroidFlavor.getprice());


       // TextView AA = (TextView) listItemView.findViewById(R.id.name);

       // AA.setText(currentAndroidFlavor.getname());

        TextView AAA = (TextView) listItemView.findViewById(R.id.desp);

        AAA.setText(currentAndroidFlavor.getsdes());

        ImageView AAAA = (ImageView) listItemView.findViewById(R.id.adimage);

        Picasso.with(ctx)
                .load(currentAndroidFlavor.getImagelink())
                .into(AAAA);

        final String sdes=currentAndroidFlavor.getsdes();
        final String name=currentAndroidFlavor.getname();
        final String email=currentAndroidFlavor.getemail();
        final String phone=currentAndroidFlavor.getphone();

        final String pincode=currentAndroidFlavor.getPincode();
        final String motive=currentAndroidFlavor.getMotive();

        final String nego=currentAndroidFlavor.getNegotiable();
        final String comdesp=currentAndroidFlavor.getCompdesp();
        //final String image=currentAndroidFlavor.getImagelink();



//        final String cno=currentAndroidFlavor.getC_no();
//
//        TextView AB = (TextView) listItemView.findViewById(R.id.element2);
//
//
//        AB.setText(currentAndroidFlavor.getC_name());
//        final String cname=currentAndroidFlavor.getC_name();
//
//        TextView ABC = (TextView) listItemView.findViewById(R.id.element3);
//
//        ABC.setText(currentAndroidFlavor.getMobile());
//
//        final String victim=currentAndroidFlavor.getVictim_name();
//        final String mobile=currentAndroidFlavor.getMobile();
//        final String age=currentAndroidFlavor.getAge();
//        final String pincode=currentAndroidFlavor.getPincode();
//        final String address=currentAndroidFlavor.getAddress();
//        final String describe=currentAndroidFlavor.getDescription();
//        final String vitness=currentAndroidFlavor.getVitness();
//        final String gender=currentAndroidFlavor.getGender();
//        final String email=currentAndroidFlavor.getEmail();
//        final String semester=currentAndroidFlavor.getSemester();
//        final String streamsection=currentAndroidFlavor.getStream_section();
//        final String enroll=currentAndroidFlavor.getEnrollmentno();
//        final String state=currentAndroidFlavor.getState();
//

//
//        listItemView.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v)
//            {
//                // do something when the button is clicked
//
//                Intent intent;
//                intent = new Intent(ctx,ViewComplaintdetails.class);
//                intent.putExtra("key1",cno);
//                intent.putExtra("key2",cname);
//                intent.putExtra("key3",victim);
//
//                intent.putExtra("key7",email);
//                intent.putExtra("key8",mobile);
//                intent.putExtra("key9",age);
//
//                intent.putExtra("key10",semester);
//                intent.putExtra("key11",streamsection);
//                intent.putExtra("key12",enroll);
//
//                intent.putExtra("key13",gender);
//                intent.putExtra("key14",pincode);
//                intent.putExtra("key15",address);
//
//                intent.putExtra("key16",state);
//                intent.putExtra("key17",describe);
//                intent.putExtra("key18",vitness);
//
//
//
//
//                ctx.startActivity(intent);
//
//
//            }
//        });
//
//




        listItemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                // do something when the button is clicked

                Intent intent;
                intent = new Intent(ctx,Ad_com_info.class);
                intent.putExtra("key1",price);

                intent.putExtra("key2",sdes);
                intent.putExtra("key3",name);
                intent.putExtra("key4",email);
                intent.putExtra("key5",phone);
                intent.putExtra("key6",pincode);
                intent.putExtra("key7",motive);
                intent.putExtra("key8",nego);
                intent.putExtra("key9",comdesp);
              //  intent.putExtra("key10",image);




                ctx.startActivity(intent);


            }
        });


        return listItemView;


    }


}
