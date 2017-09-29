package com.micro.testgreengao.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.micro.testgreengao.NoteType;
import com.micro.testgreengao.R;
import com.micro.testgreengao.bean.Note;
import com.micro.testgreengao.bean.User;
import com.micro.testgreengao.greendao.gen.NoteDao;
import com.micro.testgreengao.utils.DBHelper;
import com.micro.testgreengao.utils.LogUtils;

import java.util.Date;
import java.util.List;

public class MultiAnnotationActivity extends AppCompatActivity {

    TextView mTvInfo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_annotation);

        mTvInfo = (TextView) findViewById(R.id.id_tv_info);
    }

    public void btnAdd(View view) {
        NoteDao noteDao = DBHelper.getInstance(this).getDaoSession().getNoteDao();
        Note note = new Note(null,"good","nice",new Date(), NoteType.PICTURE);
        Long insertId = noteDao.insert(note);

        LogUtils.d("insertId : " + insertId);
    }

    public void btnLoadAll(View view) {
        List<Note> noteList = DBHelper.getInstance(this).getDaoSession().loadAll(Note.class);

        StringBuilder sb = new StringBuilder();

        for(Note u : noteList) {
            sb.append(u.toString());
            sb.append("\n");
        }

        LogUtils.d("allUserMessage:" + sb);

        mTvInfo.setText(sb.toString());

    }
}
