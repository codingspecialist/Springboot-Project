package org.techtown.bsymapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.techtown.bsymapp.Login.nick;

public  class ContentMenuAdapter extends RecyclerView.Adapter<ContentMenuAdapter.ViewHolder>
        implements OnContentMenuItemClickListener {
    ArrayList<ContentMenu> items = new ArrayList<ContentMenu>();
    ArrayList<String> urls = new ArrayList<String>();
    OnContentMenuItemClickListener listener;
   // private static final String BASE = "http://192.168.200.138:8000";
   private static final String BASE = "http://192.168.0.4:9000";



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.contentitem, viewGroup, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        final ContentMenu item = items.get(position);
        viewHolder.setItem(item);
        final Context context = viewHolder.imageView2.getContext();
        Picasso.with(context).load(urls.get(position)).fit().into(viewHolder.imageView2);

        viewHolder.cbSelect.setOnCheckedChangeListener(null);
        viewHolder.cbSelect.setChecked(item.isLikeCheck());

        if (nick.equals("로그인 해주세요")) {
            viewHolder.cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //Toast.makeText(context, "로그인이 필요한 서비스 입니다.", Toast.LENGTH_SHORT).show();
                    buttonView.setChecked(false);
                    AlertDialog.Builder alert_confirm = new AlertDialog.Builder(context);
                    alert_confirm.setMessage("로그인이 필요한 서비스 입니다.");
                    alert_confirm.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            context.startActivity(new Intent(context,Login.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        }
                    });
                    AlertDialog alert =alert_confirm.create();
                    alert.setTitle("알림");
                    alert.show();


                }
            });
        }
        else  if(!nick.equals("로그인 해주세요")){
            viewHolder.cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    item.setLikeCheck(isChecked);
                    if (item.isLikeCheck()) {
                        final Call<ContentMenu> check2 = ContentMenuAdapter.RetrofitServiceImplFactory.serverPost().check(nick, item.getId());
                        check2.enqueue(new Callback<ContentMenu>() {
                            @Override
                            public void onResponse(Call<ContentMenu> call, Response<ContentMenu> response) {
                                final ContentMenu message = response.body();
                                Toast.makeText(context,"보관함에 담겼습니다.",Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(Call<ContentMenu> call, Throwable t) {
                                Log.d("test1", "통신x");
                            }
                        });

                    } else {
                        final Call<ContentMenu> check2 = ContentMenuAdapter.RetrofitServiceImplFactory.serverPost().uncheck(nick, item.getId());
                        check2.enqueue(new Callback<ContentMenu>() {
                            @Override
                            public void onResponse(Call<ContentMenu> call, Response<ContentMenu> response) {
                                final ContentMenu message = response.body();
                                Toast.makeText(context,"보관함 해제.",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<ContentMenu> call, Throwable t) {
                                Log.d("test1", "xxx");
                            }
                        });
                    }
                }

            });
        }
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(ContentMenu item) {
        items.add(item);
    }
    public void addItem(ContentMenu item, String url){
        items.add(item);
        urls.add(url);
    }
    public void  checkdConfirm(int position){
        ContentMenu item =items.get(position);
        item.setLikeCheck(!item.isLikeCheck());
    }

    public void setItems(ArrayList<ContentMenu> items) {
        this.items = items;
    }

    public ContentMenu getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, ContentMenu item) {
        items.set(position, item);
    }

    public void setOnItemClickListener(OnContentMenuItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView2;
        TextView textView;
        CheckBox cbSelect;

        public ViewHolder(View itemView, final OnContentMenuItemClickListener listener) {
            super(itemView);
            imageView2 = itemView.findViewById(R.id.imageView22);
            textView  = itemView.findViewById(R.id.textView13);
            cbSelect =(CheckBox)itemView.findViewById(R.id.cbSelect2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }
        public void setItem(ContentMenu item) {
            imageView2.setImageResource(item.getImage());
            textView.setText(Html.fromHtml(item.getTitle()));
            textView.setSingleLine(true);
        }
    }
    static class RetrofitServiceImplFactory {
        private static Retrofit getretrofit() {
            return new Retrofit.Builder()
                    .baseUrl(BASE)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }

        public static ApiService serverPost() {
            return getretrofit().create(ApiService.class);
        }
    }

}